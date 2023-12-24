package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.InvoiceDTO;
import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.exception.InvoiceNotFoundException;
import com.coderhouse.clientservice.model.Invoice;
import com.coderhouse.clientservice.model.InvoiceDetail;
import com.coderhouse.clientservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing invoices.
 * This class provides functionality to create and retrieve invoices,
 * handling the calculations and conversions between DTOs and entity models.
 * It interacts with InvoiceRepository for database operations.
 * <p>
 * @author: Carolina Pereira
 */
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductService productService;
    private final WorldClockService worldClockService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ProductService productService, WorldClockService worldClockService) {
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.worldClockService = worldClockService;
    }

    /**
     * Creates a new invoice in the system.
     * Converts the provided InvoiceDTO to an Invoice entity, calculates the total, and saves it to the database.
     * Updates the stock of the sold products.
     *
     * @param invoiceDTO Invoice data transfer object.
     * @return The created invoice as a DTO.
     */
    @Transactional
    public InvoiceDTO create(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setClientId(invoiceDTO.getClientId());

        BigDecimal total = invoiceDTO.getInvoiceDetails().stream()
                .map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTotal(total);
        invoice.setCreatedAt(OffsetDateTime.now());

        List<InvoiceDetail> invoiceDetails = invoiceDTO.getInvoiceDetails().stream()
                .map(this::convertDetailDTOToEntity)
                .toList();

        invoiceDetails.forEach(detail -> {
            productService.updateStock(detail.getProductId(), detail.getQuantity());
            invoice.addDetail(detail);
        });

        Invoice savedInvoice = invoiceRepository.save(invoice);
        return convertToDTO(savedInvoice);
    }

    public InvoiceDTO findById(int id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with ID: " + id));
        return convertToDTO(invoice);
    }

    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper methods
    private Invoice convertToEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setClientId(dto.getClientId());
        invoice.setTotal(dto.getTotal());

        String worldClockTime = worldClockService.getCurrentDateTime().getCurrentDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX");
        OffsetDateTime currentDateTime = OffsetDateTime.parse(worldClockTime, formatter);
        invoice.setCreatedAt(currentDateTime);
        dto.getInvoiceDetails().forEach(detailDTO -> invoice.addDetail(convertDetailDTOToEntity(detailDTO)));
        return invoice;
    }

    private InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setClientId(invoice.getClientId());
        dto.setTotal(invoice.getTotal());
        dto.setCreatedAt(invoice.getCreatedAt());
        dto.setInvoiceDetails(invoice.getDetails().stream()
                .map(this::convertDetailToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private InvoiceDetail convertDetailDTOToEntity(InvoiceDetailsDTO dto) {
        InvoiceDetail detail = new InvoiceDetail();
        detail.setProductId(dto.getProductId());
        detail.setQuantity(dto.getQuantity());
        detail.setPrice(dto.getPrice());
        detail.setDescription(dto.getDescription());
        return detail;
    }

    // Método para convertir InvoiceDetail a InvoiceDetailsDTO
    private InvoiceDetailsDTO convertDetailToDTO(InvoiceDetail detail) {
        InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
        dto.setId(detail.getId());
        if (detail.getInvoice() != null) {
            dto.setInvoiceId(detail.getInvoice().getId()); // Obtener el ID de la factura asociada
        }
        dto.setProductId(detail.getProductId());
        dto.setQuantity(detail.getQuantity());
        dto.setPrice(detail.getPrice());
        dto.setDescription(detail.getDescription());
        return dto;
    }

    private void updateProductStock(InvoiceDetail detail) {
        productService.updateStock(detail.getProductId(), detail.getQuantity());
    }
}

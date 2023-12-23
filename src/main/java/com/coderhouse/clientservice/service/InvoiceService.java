package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.InvoiceDTO;
import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.model.Invoice;
import com.coderhouse.clientservice.model.WorldClock;
import com.coderhouse.clientservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final WorldClockService worldClockService;
    private final ProductService productService; // Agregar esta línea

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, WorldClockService worldClockService, ProductService productService) { // Modificar constructor
        this.invoiceRepository = invoiceRepository;
        this.worldClockService = worldClockService;
        this.productService = productService; // Inicializar productService
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = convertToEntity(invoiceDTO);

        // Obtiene la fecha y hora actual del servicio WorldClock
        WorldClock worldClock = worldClockService.getCurrentDateTime();
        LocalDateTime currentDateTime = LocalDateTime.parse(worldClock.getCurrentDateTime());
        invoice.setCreatedAt(currentDateTime);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        // Actualiza el stock para cada detalle de la factura
        for (InvoiceDetailsDTO detail : invoiceDTO.getInvoiceDetails()) {
            productService.updateStock(detail.getProductId(), detail.getQuantity());
        }

        return convertToDTO(savedInvoice);
    }

    public InvoiceDTO create(InvoiceDTO invoiceDTO) {
        Invoice invoice = convertToEntity(invoiceDTO);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return convertToDTO(savedInvoice);
    }

    public InvoiceDTO findById(int id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + id));
        return convertToDTO(invoice);
    }

    private InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setClientId(invoice.getClientId());
        dto.setQuantity(invoice.getQuantity());
        dto.setTotal(invoice.getTotal());
        dto.setCreatedAt(invoice.getCreatedAt());
        return dto;
    }

    private Invoice convertToEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setClientId(dto.getClientId());
        invoice.setQuantity(dto.getQuantity());
        invoice.setTotal(dto.getTotal());
        invoice.setCreatedAt(dto.getCreatedAt());
        return invoice;
    }
}

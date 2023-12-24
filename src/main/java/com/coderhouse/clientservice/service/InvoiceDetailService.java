package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.exception.InvoiceNotFoundException;
import com.coderhouse.clientservice.model.Invoice;
import com.coderhouse.clientservice.model.InvoiceDetail;
import com.coderhouse.clientservice.repository.InvoiceDetailRepository;
import com.coderhouse.clientservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing invoice details.
 * This class provides functionality to create and retrieve invoice details,
 * converting between DTOs and entity models as needed.
 * It interacts with InvoiceDetailRepository and InvoiceRepository for database operations.
 * @author: Carolina Pereira
 */
@Service
public class InvoiceDetailService {
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceDetailService(InvoiceDetailRepository invoiceDetailRepository, InvoiceRepository invoiceRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Creates a new InvoiceDetail and saves it to the database.
     * Converts InvoiceDetailsDTO to InvoiceDetail entity before saving.
     *
     * @param invoiceDetailDTO Data Transfer Object containing invoice detail information.
     * @return The saved InvoiceDetail as a DTO.
     */
    public InvoiceDetailsDTO create(InvoiceDetailsDTO invoiceDetailDTO) {
        InvoiceDetail invoiceDetail = convertToEntity(invoiceDetailDTO);
        return convertToDTO(invoiceDetailRepository.save(invoiceDetail));
    }

    /**
     * Finds an InvoiceDetail by its ID.
     * Throws InvoiceNotFoundException if the invoice detail is not found.
     *
     * @param id The ID of the InvoiceDetail.
     * @return The found InvoiceDetail as a DTO.
     */
    public InvoiceDetailsDTO findById(int id) {
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InvoiceDetail not found with ID: " + id));
        return convertToDTO(invoiceDetail);
    }

    /**
     * Converts an InvoiceDetailsDTO to an InvoiceDetail entity.
     * Retrieves and sets the associated Invoice.
     * Throws InvoiceNotFoundException if the related invoice is not found.
     *
     * @param dto The InvoiceDetailsDTO to convert.
     * @return The converted InvoiceDetail entity.
     */
    private InvoiceDetail convertToEntity(InvoiceDetailsDTO dto) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        Optional<Invoice> invoice = invoiceRepository.findById(dto.getInvoiceId());
        if (invoice.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice not found with ID: " + dto.getInvoiceId());
        }
        invoiceDetail.setProductId(dto.getProductId());
        invoiceDetail.setQuantity(dto.getQuantity());
        invoiceDetail.setPrice(dto.getPrice());
        invoiceDetail.setDescription(dto.getDescription());
        return invoiceDetail;
    }

    /**
     * Converts an InvoiceDetail entity to an InvoiceDetailsDTO.
     *
     * @param invoiceDetail The InvoiceDetail entity to convert.
     * @return The converted InvoiceDetailsDTO.
     */
    private InvoiceDetailsDTO convertToDTO(InvoiceDetail invoiceDetail) {
        InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
        dto.setId(invoiceDetail.getId());
        dto.setInvoiceId(invoiceDetail.getInvoice().getId());
        dto.setProductId(invoiceDetail.getProductId());
        dto.setQuantity(invoiceDetail.getQuantity());
        dto.setPrice(invoiceDetail.getPrice());
        dto.setDescription(invoiceDetail.getDescription());
        return dto;
    }
}

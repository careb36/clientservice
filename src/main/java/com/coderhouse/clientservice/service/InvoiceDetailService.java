package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.model.InvoiceDetail;
import com.coderhouse.clientservice.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {

    private final InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    public InvoiceDetailService(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    public InvoiceDetailsDTO create(InvoiceDetailsDTO invoiceDetailDTO) {
        InvoiceDetail invoiceDetail = convertToEntity(invoiceDetailDTO);
        InvoiceDetail savedInvoiceDetail = invoiceDetailRepository.save(invoiceDetail);
        return convertToDTO(savedInvoiceDetail);
    }

    public InvoiceDetailsDTO findById(int id) {
        InvoiceDetail invoiceDetail = invoiceDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InvoiceDetail not found with ID: " + id));
        return convertToDTO(invoiceDetail);
    }

    private InvoiceDetailsDTO convertToDTO(InvoiceDetail invoiceDetail) {
        InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
        dto.setId(invoiceDetail.getId());
        dto.setInvoiceId(invoiceDetail.getInvoiceId());
        dto.setProductId(invoiceDetail.getProductId());
        dto.setQuantity(invoiceDetail.getQuantity());
        dto.setPrice(invoiceDetail.getPrice());
        dto.setDescription(invoiceDetail.getDescription());
        return dto;
    }

    private InvoiceDetail convertToEntity(InvoiceDetailsDTO dto) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setInvoiceId(dto.getInvoiceId());
        invoiceDetail.setProductId(dto.getProductId());
        invoiceDetail.setQuantity(dto.getQuantity());
        invoiceDetail.setPrice(dto.getPrice());
        invoiceDetail.setDescription(dto.getDescription());
        return invoiceDetail;
    }
}

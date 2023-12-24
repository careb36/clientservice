package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link InvoiceDetailsDTO}.
 * This controller provides endpoints for CRUD operations related to invoice details.
 */
@RestController
@RequestMapping("/api/invoiceDetails")
public class InvoiceDetailController {

    private final InvoiceDetailService invoiceDetailService;

    /**
     * Constructs an InvoiceDetailController with a dependency on InvoiceDetailService.
     *
     * @param invoiceDetailService The service to manage operations on invoice details.
     */
    @Autowired
    public InvoiceDetailController(InvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }

    /**
     * Creates a new invoice detail.
     * Accepts an InvoiceDetailsDTO object and uses the service to persist it.
     *
     * @param invoiceDetailsDTO Data Transfer Object containing details of the invoice.
     * @return ResponseEntity containing the created invoice detail and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<InvoiceDetailsDTO> createInvoiceDetails(@RequestBody InvoiceDetailsDTO invoiceDetailsDTO) {
        InvoiceDetailsDTO newInvoiceDetails = invoiceDetailService.create(invoiceDetailsDTO);
        return new ResponseEntity<>(newInvoiceDetails, HttpStatus.CREATED);
    }

    /**
     * Retrieves invoice details by ID.
     * Searches for an invoice detail with the given ID and returns it.
     *
     * @param id The ID of the invoice detail to retrieve.
     * @return ResponseEntity containing the found invoice detail and HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailsDTO> getInvoiceDetailsById(@PathVariable int id) {
        InvoiceDetailsDTO invoiceDetails = invoiceDetailService.findById(id);
        return new ResponseEntity<>(invoiceDetails, HttpStatus.OK);
    }

    // Additional methods for updating and deleting invoice details can be added here.
}
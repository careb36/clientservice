package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.InvoiceDTO;
import com.coderhouse.clientservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link InvoiceDTO}.
 * Provides endpoints for performing CRUD operations on invoices.
 */
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    /**
     * Constructs an InvoiceController with the given InvoiceService.
     *
     * @param invoiceService The service to manage invoice data operations.
     */
    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Creates a new invoice.
     *
     * @param invoiceDTO The Invoice Data Transfer Object containing invoice data.
     * @return ResponseEntity containing the created invoice and HTTP status.
     */
    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO newInvoice = invoiceService.create(invoiceDTO);
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }

    /**
     * Retrieves an invoice by its ID.
     *
     * @param id The ID of the invoice to retrieve.
     * @return ResponseEntity containing the found invoice and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable int id) {
        InvoiceDTO invoice = invoiceService.findById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    /**
     * Retrieves all invoices.
     *
     * @return ResponseEntity containing a list of all invoices and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    // Additional endpoints for update and delete operations can be added here.
}


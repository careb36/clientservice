package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.InvoiceDetailsDTO;
import com.coderhouse.clientservice.service.InvoiceDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoiceDetails")
public class InvoiceDetailController {

    private final InvoiceDetailService invoiceDetailService;

    @Autowired
    public InvoiceDetailController(InvoiceDetailService invoiceDetailService) {
        this.invoiceDetailService = invoiceDetailService;
    }
    @Valid
    @PostMapping
    public ResponseEntity<InvoiceDetailsDTO> createInvoiceDetails(@RequestBody InvoiceDetailsDTO invoiceDetailsDTO) {
        InvoiceDetailsDTO newInvoiceDetails = invoiceDetailService.create(invoiceDetailsDTO);
        return new ResponseEntity<>(newInvoiceDetails, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailsDTO> getInvoiceDetailsById(@PathVariable int id) {
        InvoiceDetailsDTO invoiceDetails = invoiceDetailService.findById(id);
        return new ResponseEntity<>(invoiceDetails, HttpStatus.OK);
    }

}

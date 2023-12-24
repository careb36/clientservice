package com.coderhouse.clientservice.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}


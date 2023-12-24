package com.coderhouse.clientservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * This class uses Spring's @ControllerAdvice to provide centralized exception handling
 * across all @RequestMapping methods through @ExceptionHandler methods.
 *
 * It captures specific exceptions thrown by service methods and transforms them into
 * appropriate HTTP responses, improving the API's error reporting and client-side error handling.
 *
 * The methods handle various types of exceptions, each returning a ResponseEntity object
 * that encapsulates the error message and a corresponding HTTP status code.
 *
 * @author Carolina Pereira
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EntityNotFoundExceptions typically thrown when an entity is not found in the database.
     * Translates the exception to a 404 Not Found HTTP response.
     *
     * @param exception The caught EntityNotFoundException.
     * @return A ResponseEntity with the exception's message and HTTP status 404.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general exceptions that are not caught by other specific handlers.
     * This acts as a catch-all for exceptions, translating them to a 500 Internal Server Error HTTP response.
     *
     * @param exception The caught Exception.
     * @return A ResponseEntity with the exception's message and HTTP status 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Additional exception handlers for ProductNotFoundException, InsufficientStockException,
     * ClientNotFoundException, and InvoiceNotFoundException follow a similar pattern,
     * handling specific custom exceptions and mapping them to appropriate HTTP responses.
     */

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<String> handleInvoiceNotFoundException(InvoiceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

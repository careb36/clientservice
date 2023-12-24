package com.coderhouse.clientservice.controller;

import com.coderhouse.clientservice.dto.ProductDTO;
import com.coderhouse.clientservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link ProductDTO}.
 * This controller offers endpoints for CRUD operations on products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor for ProductController.
     * Injects ProductService to handle business logic related to products.
     *
     * @param productService Service responsible for product operations.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint to create a new product.
     * Receives a ProductDTO, persists it, and returns the created product.
     *
     * @param productDTO Data Transfer Object representing a product.
     * @return ResponseEntity containing the created product and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO newProduct = productService.create(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve a product by its ID.
     * Searches and returns the product if found.
     *
     * @param id The unique identifier of the product.
     * @return ResponseEntity containing the requested product and HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        ProductDTO product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all products.
     * Returns a list of all products.
     *
     * @return ResponseEntity containing the list of products and HTTP status code.
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint to update a product.
     * Updates the product with the given ID and returns the updated product.
     *
     * @param id The ID of the product to update.
     * @param productDTO The updated product data.
     * @return ResponseEntity containing the updated product and HTTP status code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.update(id, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a product.
     * Deletes the product with the specified ID.
     *
     * @param id The ID of the product to delete.
     * @return ResponseEntity with HTTP status code indicating the result.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

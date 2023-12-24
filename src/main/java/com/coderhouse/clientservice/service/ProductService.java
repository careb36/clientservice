package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.dto.ProductDTO;
import com.coderhouse.clientservice.exception.InsufficientStockException;
import com.coderhouse.clientservice.exception.ProductNotFoundException;
import com.coderhouse.clientservice.model.Product;
import com.coderhouse.clientservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing products.
 * This class provides functionality to create, retrieve, update, and manage stock of products,
 * converting between DTOs and entity models as needed.
 * It interacts with ProductRepository for database operations.
 * <p>
 * @author: Carolina Pereira
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product in the system.
     * Converts the provided ProductDTO to a Product entity and saves it to the database.
     *
     * @param productDTO Product data transfer object.
     * @return The created product as a DTO.
     */
    public ProductDTO create(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO findById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        return convertToDTO(product);
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public ProductDTO update(int id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        updateProductDetails(existingProduct, productDTO);
        Product updatedProduct = productRepository.save(existingProduct);
        return convertToDTO(updatedProduct);
    }

    /**
     * Updates the stock of a product.
     * Throws InsufficientStockException if the stock is not enough for the sale.
     *
     * @param productId The ID of the product.
     * @param quantitySold The quantity of the product sold.
     */
    public void updateStock(int productId, int quantitySold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        int newStock = product.getStock() - quantitySold;
        if (newStock < 0) {
            throw new InsufficientStockException("Insufficient stock for product ID: " + productId);
        }
        product.setStock(newStock);
        productRepository.save(product);
    }

    // Helper methods
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }

    private void updateProductDetails(Product product, ProductDTO dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
    }

    public void delete(int id) {
    }
}

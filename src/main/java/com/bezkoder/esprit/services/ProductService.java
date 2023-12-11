package com.bezkoder.esprit.services;


import com.bezkoder.esprit.models.Product;
import com.bezkoder.esprit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setAvailable(productDetails.isAvailable());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }).orElseGet(() -> {
            productDetails.setId(id);
            return productRepository.save(productDetails);
        });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
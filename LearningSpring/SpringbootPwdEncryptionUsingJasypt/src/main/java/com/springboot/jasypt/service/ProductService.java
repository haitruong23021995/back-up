package com.springboot.jasypt.service;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//lombok annotation
@Slf4j
//spring annotation
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public boolean defaultProductsPresent() {
        return repository.count() > 0;
    }

    public void saveAll(final List<Product> products) {
        log.info("Saving all products");
        repository.saveAll(products);
    }

    public List<Product> getProducts() {
        log.info("Returning all products");
        return repository.findAll();
    }

    //NOTE - we are only considering the happy path.
    //you are free to add the failure case where if product not found
    //throw an NotFoundException and return HTTP 404 error response
    //code
    public Product getProduct(final UUID referenceId) {
        log.info("Returning product by ref-id = {}", referenceId);
        return repository.findByRefId(referenceId);
    }
}

package com.springboot.jasypt.service;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceUsingCons {

    private final ProductRepository productRepository;

    private final LogService logService;

    public List<Product> getProducts() {
        logService.addLog("Returning all products");
//        return null;
        return productRepository.findAll();
    }

    public List<Product> findByNameAndPriceOrRefId(String name, double price, UUID refId) {
        return productRepository.findByNameAndPriceOrRefId(name, price, refId);
    }
}

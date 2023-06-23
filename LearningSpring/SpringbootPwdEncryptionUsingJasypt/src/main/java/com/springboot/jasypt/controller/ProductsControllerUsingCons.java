package com.springboot.jasypt.controller;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.model.ProductRequest;
import com.springboot.jasypt.service.ProductServiceUsingCons;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//NOTE - We are not mapping the Product entity with a Product response dto
//you are free to map the Product entity with a Product response dto to
//avoid the id in the response

//spring annotations
@RestController
@RequiredArgsConstructor
@RequestMapping("/usingCons")
public class ProductsControllerUsingCons {

    private final ProductServiceUsingCons service;

    //swagger annotations
    @Operation(summary = "Return product list")
    @ApiResponse(responseCode = "200", description = "The request has succeeded")
    //spring annotations
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @PostMapping("/productsByValues")
    public List<Product> getProducts(@RequestBody ProductRequest productRequest) {
        return service.findByNameAndPriceOrRefId(productRequest.getName(),
                productRequest.getPrice(), productRequest.getRefId());
    }

}

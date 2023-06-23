package com.springboot.jasypt.controller;

import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.service.FooService;
import com.springboot.jasypt.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//NOTE - We are not mapping the Product entity with a Product response dto
//you are free to map the Product entity with a Product response dto to
//avoid the id in the response

//spring annotations
@RestController
@RequestMapping("/api")
//swagger annotation
@Tag(name = "Product resource REST endpoints", description = "Shows the product info")
public class ProductsController {

    @Autowired
    private ProductService service;

    @Autowired
    private FooService fooService;


    //swagger annotations
    @Operation(summary = "Return product list")
    @ApiResponse(responseCode = "200", description = "The request has succeeded")
    //spring annotations
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {
        return service.getProducts();
    }

    //swagger annotations
    @Operation(summary = "Return product by reference id")
    @ApiResponse(responseCode = "200", description = "The request has succeeded")
    //spring annotations
    //NOTE - we are only considering the happy path.
    //you are free to add the failure case where if product not found
    //throw an NotFoundException and return HTTP 404 error response
    //code
    @GetMapping("/product/{refId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("refId") final UUID refId) {
        return service.getProduct(refId);
    }

    @RequestMapping("/foo")
    public String foo(){
        int randomNumber = RandomUtils.nextInt();
        fooService.setFoo(randomNumber);
        int retrievedNumber = fooService.getFoo();
        if (randomNumber != retrievedNumber) {
            return "Error! Foo that was retrieved was not the same as the one that was set";
        }

        return "OK";
    }
}

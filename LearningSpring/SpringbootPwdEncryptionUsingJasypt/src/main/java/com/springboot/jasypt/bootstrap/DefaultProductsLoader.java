package com.springboot.jasypt.bootstrap;

import com.github.javafaker.Faker;
import com.springboot.jasypt.model.Product;
import com.springboot.jasypt.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//lombok annotation
@Slf4j
//spring annotation
@Component
public class DefaultProductsLoader implements CommandLineRunner {

    @Autowired
    private ProductService service;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        if (!service.defaultProductsPresent()) {
            service.saveAll(products());
        } else {
            log.info("Skipping insert as default present");
        }
    }

    private List<Product> products() {
        final List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(create());
        }

        return products;
    }

    private Product create() {
        return Product.builder()
                .name(faker.commerce().productName())
                .price(Double.parseDouble(faker.commerce().price()))
                .refId(UUID.randomUUID())
                .build();
    }
}

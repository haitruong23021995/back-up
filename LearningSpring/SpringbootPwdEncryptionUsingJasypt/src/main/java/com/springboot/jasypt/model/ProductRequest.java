package com.springboot.jasypt.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductRequest {

    int id;
    String name;
    double price;
    UUID refId;
}

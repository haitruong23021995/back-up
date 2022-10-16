package com.springboot.jasypt.repository;

import com.springboot.jasypt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//spring annotation
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //get product by reference id
    Product findByRefId(UUID refId);
}

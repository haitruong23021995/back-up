package com.springboot.jasypt.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

//spring jpa annotations
@Entity
@Table(name = "product_tbl")
//lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//spring annotation
@Component
//swagger annotation
@Schema
public class Product {

    @Schema(description = "Product id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Schema(description = "Product name")
    @Column(name = "product_name")
    String name;
    @Schema(description = "Product price")
    @Column(name = "product_price")
    double price;
    @Schema(description = "Product reference id")
    @Column(name = "reference_id")
    UUID refId;
}

package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "news")
@Data
public class News extends BassEntity{

    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

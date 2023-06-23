package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category extends BassEntity{

    private String code;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<News> news;
}

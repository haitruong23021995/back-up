package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
@Data
public class Comment extends BassEntity{

    private String content;

}

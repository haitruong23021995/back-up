package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String modifiedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifiedDate;
}

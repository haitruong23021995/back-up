package com.example.demo.dto;

import com.example.demo.entity.BassEntity;
import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends BassEntity {

//    private String code;
    private String name;
}

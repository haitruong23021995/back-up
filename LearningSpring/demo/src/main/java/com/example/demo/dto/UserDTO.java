package com.example.demo.dto;

import com.example.demo.entity.BassEntity;
import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BassEntity {

    private String username;
    private String password;
    private String fullName;
    private int status;
    private List<RoleDTO> roles;
}

package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    List<UserDTO> getUsers();
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

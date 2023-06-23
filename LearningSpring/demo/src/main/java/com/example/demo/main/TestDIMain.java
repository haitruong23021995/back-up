package com.example.demo.main;

import com.example.demo.main.controller.UserController;

public class TestDIMain {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.send("Hello Dependency injection pattern");
    }
}

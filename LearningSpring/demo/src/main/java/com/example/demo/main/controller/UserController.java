package com.example.demo.main.controller;

import com.example.demo.main.service.EmailService;

public class UserController {
    private EmailService emailService;

    public void send(String message) {
        emailService.sendEmail(message);
    }
}

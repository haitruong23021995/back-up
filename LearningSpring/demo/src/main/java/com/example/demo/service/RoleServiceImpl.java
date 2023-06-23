package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public final class RoleServiceImpl {

    public final String pancardNumber = "hai";


//    public RoleServiceImpl(String pancardNumber) {
//        this.pancardNumber = pancardNumber;
//    }

    public String getPancardNumber() {
        return pancardNumber;
    }
}

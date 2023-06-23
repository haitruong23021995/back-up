package com.javainuse.service;

import com.javainuse.service.impl.EmployeeServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void test(){
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
    }
}

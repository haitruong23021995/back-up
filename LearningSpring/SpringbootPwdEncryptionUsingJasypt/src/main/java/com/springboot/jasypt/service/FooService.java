package com.springboot.jasypt.service;

import org.springframework.stereotype.Service;

@Service
public class FooService {
    // note: foo is a shared instance variable
    private int foo;

    public int getFoo() {
        return foo;
    }

    public void setFoo(int foo) {
        this.foo = foo;
    }
}

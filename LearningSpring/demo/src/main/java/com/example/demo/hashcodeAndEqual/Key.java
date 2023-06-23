package com.example.demo.hashcodeAndEqual;

import org.springframework.util.StringUtils;

public class Key {
    String key;

    Key(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        System.out.println(key.charAt(0));
        return key.charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        return key.equals(String.valueOf(obj));
    }
}
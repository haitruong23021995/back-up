package com.example.demo.LD.main;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateContextRequest {

    private String name;
    private String kind;
    private String key;
    private boolean value;
}

package com.example.demo.dto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public final class EmployeeImuTable {
  private final String pancardNumber = "a";

//  public EmployeeImuTable(String pancardNumber) {
//    this.pancardNumber = pancardNumber;
//  }

  public String getPancardNumber() {
    return pancardNumber;
  }
}
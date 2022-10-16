package com.example.demo.dto;

import org.springframework.stereotype.Service;

@Service
public class Employee {
  public String pancardNumber;

  public Employee() {
    System.out.println(" alooo");
  }

  public Employee(String pancardNumber) {
    this.pancardNumber = pancardNumber;
  }

  public String getPancardNumber() {
    return pancardNumber;
  }
}
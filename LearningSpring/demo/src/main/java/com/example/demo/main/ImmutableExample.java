package com.example.demo.main;

import com.example.demo.dto.Employee;
import com.example.demo.dto.EmployeeImuTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ImmutableExample {

  @Autowired
  private Employee employee;

  public static void main(String[] args) {
    String pancardNumber = "pancardNumber String";
//    EmployeeImuTable employee = new EmployeeImuTable(pancardNumber);
//    employee.pancardNumber = pancardNumber;
//    log.info(employee.getPancardNumber());
//    employee.pancardNumber = "123";

  }
}

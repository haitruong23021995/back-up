package com.example.demo.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class IntegerExample {
    public static void main(String[] args) {
        Integer age = null;
        int a;
        Long b = null;
//        log.debug(" Integer Age ={}", b);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1L, "hai"));
        customerList.add(new Customer(2L, "hai"));
        customerList.add(new Customer(3L, "hien"));
        customerList.add(new Customer(4L, "hien"));
        Map<String, List<Customer>> map = new HashMap<>();
        Map<String, Customer> map2 = new HashMap<>();
        Map<String, Customer> map3 = new HashMap<>();
//        map = customerList.stream().collect(Collectors.toMap(item -> item.getName(), customerList));
//        log.info("{}",customerList.stream().collect(Collectors.toSet()));
//        log.info("{}",customerList.stream().filter()
//                .sorted(Comparator.comparing(Customer::getName)) // sort while streaming
//                .collect(Collectors.toCollection(LinkedHashSet::new)));
    }


}

@Data
@AllArgsConstructor
class Customer {
    private Long id;
    private String name;
}

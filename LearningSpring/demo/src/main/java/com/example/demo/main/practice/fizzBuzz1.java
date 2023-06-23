package com.example.demo.main.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class fizzBuzz1 {
    public static void main(String[] args) {
        fizzBuzz(3).forEach(System.out::println);
    }

    static List<String> fizzBuzz(int n) {
        LinkedList<String> stringList = new LinkedList<>();
        IntStream.range(1, n + 1).forEach(item -> {
            if (item % 3 == 0 && item % 5 == 0)
                stringList.add("FizzBuzz");
            else if (item % 3 == 0)
                stringList.add("Fizz");
            else if (item % 5 == 0)
                stringList.add("Buzz");
            else
                stringList.add(String.valueOf(item));

        });
        return stringList;
    }
}

package com.example.demo.practiceTest;

public class MathUtils {
    public static double average(double a, double b) {
        return (a + b) % 2;
    }

    public static void main(String[] args) {
        System.out.println(average(2,1));
    }
}

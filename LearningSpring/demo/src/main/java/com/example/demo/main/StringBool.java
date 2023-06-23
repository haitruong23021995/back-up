package com.example.demo.main;

public class StringBool {
    public static void main(String args[]) {
        StringBuilder();
    }

    static void String() {
        String NewString = "Hello";
        NewString.concat("World");
        System.out.println(NewString);
    }

    static void StringBuilder() {
        StringBuilder stringBuilder = new StringBuilder("Hello");
        stringBuilder.append("World");
        System.out.println(stringBuilder);
    }
}

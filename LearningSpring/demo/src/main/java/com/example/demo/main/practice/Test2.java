package com.example.demo.main.practice;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Test2 {

    public static void main(String[] args) {
        System.out.println(convertToInt("MCMXCIV"));
    }

    static long convertToInt(String s) {
        int specialInt = 0;
        if (s.contains("IV")) {
            specialInt = specialInt + 4 * StringUtils.countOccurrencesOf(s, "IV");
            s = s.replaceAll("IV", "");
        }
        if (s.contains("IX")) {
            specialInt = specialInt + 9 * StringUtils.countOccurrencesOf(s, "IX");
            s = s.replaceAll("IX", "");
        }
        if (s.contains("XL")) {
            specialInt = specialInt + 40 * StringUtils.countOccurrencesOf(s, "XL");
            s = s.replaceAll("XL", "");
        }
        if (s.contains("XC")) {
            specialInt = specialInt + 90 * StringUtils.countOccurrencesOf(s, "XC");
            s = s.replaceAll("XC", "");
        }
        if (s.contains("CD")) {
            specialInt = specialInt + 400 * StringUtils.countOccurrencesOf(s, "CD");
            s = s.replaceAll("CD", "");
        }
        if (s.contains("CM")) {
            specialInt = specialInt + 900 * StringUtils.countOccurrencesOf(s, "CM");
            s = s.replaceAll("CM", "");
        }
        return Arrays.stream(s.split("")).map(item -> {
            switch (item) {
                case "I":
                    return 1;
                case "V":
                    return 5;
                case "X":
                    return 10;
                case "L":
                    return 50;
                case "C":
                    return 100;
                case "D":
                    return 500;
                case "M":
                    return 1000;
            }
            return 0;
        }).reduce(0, Integer::sum
        ) + specialInt;
    }
}

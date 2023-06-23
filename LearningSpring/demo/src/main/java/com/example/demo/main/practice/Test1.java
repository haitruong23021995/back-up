package com.example.demo.main.practice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Test1 {

    public static void main(String[] args) {
        System.out.println(convertToInt("MCMXCIV"));
    }

    static long convertToInt(String str) {
        AtomicReference<Boolean> specialValue1 = new AtomicReference<>(false);
        AtomicReference<Boolean> specialValue2 = new AtomicReference<>(false);
        AtomicReference<Boolean> specialValue3 = new AtomicReference<>(false);
        AtomicReference<Boolean> specialValue4 = new AtomicReference<>(false);
        AtomicReference<Boolean> specialValue5 = new AtomicReference<>(false);
        AtomicReference<Boolean> specialValue6 = new AtomicReference<>(false);
        List<String> list = Arrays.asList(str.split(""));
        return list.stream().map(item -> {
            switch (item) {
                case "I":
                    specialValue1.set(list.indexOf(item) == 0);
                    return 1;
                case "V":
                    specialValue2.set(list.indexOf(item) == 1);
                    return 5;
                case "X":
                    specialValue2.set(list.indexOf(item) == 1);
                    specialValue3.set(list.indexOf(item) == 0);
                    return 10;
                case "L":
                    specialValue4.set(list.indexOf(item) == 1);
                    return 50;
                case "C":
                    specialValue4.set(list.indexOf(item) == 1);
                    specialValue5.set(list.indexOf(item) == 0);
                    return 100;
                case "D":
                    specialValue6.set(list.indexOf(item) == 1);
                    return 500;
                case "M":
                    specialValue6.set(list.indexOf(item) == 1);
                    return 1000;
            }
            return 0;
        }).reduce(0, (a, b) -> {
            if (list.size() == 2 && ((specialValue1.get() && specialValue2.get())
                    || (specialValue3.get() && specialValue4.get()))
                    || (specialValue5.get() && specialValue6.get())){
                return b - a;
            } else
            return a + b;
        });
    }
}

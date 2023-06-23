package com.example.demo.main.ubitec;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        RangeLocal<Integer> rangeLocalInt = RangeLocal.open(5,7);
        System.out.println(rangeLocalInt.contains(6));
        RangeLocal<BigDecimal> rangeLocalDecimals =
                RangeLocal.open(BigDecimal.valueOf(new Double("1.32432")),
                        BigDecimal.valueOf(new Double("1.324323423423423423423")));
        System.out.println(rangeLocalDecimals.contains(new BigDecimal("1.3243234")));
        RangeLocal<LocalDate> rangeLocalLD = RangeLocal.open(
                LocalDate.of(2016, Month.SEPTEMBER, 11),
                LocalDate.of(2017, Month.JUNE, 30));
        System.out.println(rangeLocalLD.contains(LocalDate.of(2016, Month.SEPTEMBER, 12)));
        System.out.println("----------------------");
        RangeLocal<Integer> lessThanFive = RangeLocal.lessThan(5); // [Infinitive, 5)
        System.out.println(lessThanFive.contains(5)); // false;
        System.out.println(lessThanFive.contains(-9000)); // true;
        RangeLocal<Integer> atLeastFive = RangeLocal.atLeast(5); // [5, Infinitive]
        System.out.println(atLeastFive.contains(5)); // true;
        System.out.println(atLeastFive.contains(4)); // false;

        RangeLocal<Integer> atMostFive = RangeLocal.atMost(5); // [Infinitive, 5]
        System.out.println(atMostFive.contains(5)); // true
        System.out.println(atMostFive.contains(-234234)); // true;
        System.out.println(atMostFive.contains(6)); // false;

        System.out.println("afterEpoch");
        RangeLocal<LocalDate> afterEpoch = RangeLocal.greaterThan(LocalDate.of(1900, Month.JANUARY, 1)); // (1900-01-01, Infinitive]
        System.out.println(afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28))); // true;
        System.out.println(afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1))); // false;
        System.out.println(afterEpoch.contains(LocalDate.of(1900, Month.JANUARY, 1)));// false;


        RangeLocal<String> all = RangeLocal.all(); // [Infinitive, Infinitive]
        System.out.println(all.contains("anything")); // true;
        System.out.println(all.contains("")); // true;
        System.out.println(all.contains(null)); // true;
        System.out.println(all.contains(123));//true

        System.out.println("----------------------");
        RangeLocal<Integer> lessThan100 = RangeLocal.lessThan(100);
        System.out.println(lessThan100.toString().equals("[Infinitive, 100)"));

        RangeLocal<LocalDate> within2020 = RangeLocal.closed(
                LocalDate.of(2020, Month.JANUARY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31));
        System.out.println(within2020.toString().equals("[2020-01-01, 2020-12-31]"));

        RangeLocal<BigDecimal> withinBD = RangeLocal.open(BigDecimal.valueOf(new Double("1.32432")),
                BigDecimal.valueOf(new Double("1.67334")));
        System.out.println(withinBD.toString().equals("[1.32432, 1.67334]"));

        System.out.println("------------");
        RangeLocal<Integer> parseInt = RangeLocal.parse("[Infinitive, 100)", Integer.class);
        System.out.println(parseInt.contains(100));
        System.out.println(parseInt.contains(-12));
        System.out.println("----------");
        RangeLocal<LocalDate> parseLD = RangeLocal.parse("[Infinitive, 2020-12-31)", LocalDate.class);
        System.out.println(parseLD.contains(LocalDate.of(2020, Month.JANUARY, 31)));//true
        System.out.println(parseLD.contains(LocalDate.of(3000, Month.SEPTEMBER, 01)));//false

        RangeLocal<Integer> parseInt2 = RangeLocal.parse("[3, 9)", Integer.class);
        System.out.println(parseInt2.contains(4));//true
        System.out.println(parseInt2.contains(9));//false

    }
}

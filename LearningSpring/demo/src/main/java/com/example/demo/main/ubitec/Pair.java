package com.example.demo.main.ubitec;

import java.util.Arrays;

public class Pair implements Comparable<Pair> {
    String firstName;
    String lastName;

    public Pair(String x, String y) {
        this.firstName = x;
        this.lastName = y;
    }

    public String toString() {
        return "( " + firstName + " , " + lastName + " )";
    }

    @Override
    public int compareTo(Pair a) {
        // if the string are not equal
        if (this.firstName.compareTo(a.firstName) != 0) {
            return this.firstName.compareTo(a.firstName);
        } else {
            // we compare lastName if firstNames are equal
            return this.lastName.compareTo(a.lastName);
        }
    }

    public static void main(String[] args) {

        int n = 4;
        Pair arr[] = new Pair[n];
        arr[0] = new Pair("raj", "kashup");
        arr[1] = new Pair("rahul", "singh");//this
        arr[2] = new Pair("reshmi", "dubey");
        arr[3] = new Pair("rahul", "jetli");

        // Sorting the array
        Arrays.sort(arr);

        // printing the
        // Pair array
        print(arr);
    }

    public static void print(Pair[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

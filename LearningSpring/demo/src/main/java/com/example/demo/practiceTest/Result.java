package com.example.demo.practiceTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Result {

    /*
     * Complete the 'getMinMachines' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY start
     *  2. INTEGER_ARRAY end
     */

    public static void main(String[] args) throws IOException {
        getMinMachines(Arrays.asList(1, 8, 3, 9, 6), Arrays.asList(7, 9, 6, 14, 7));
    }

    public static int getMinMachines(List<Integer> start1, List<Integer> end2) throws IOException {
        List<Integer> result = new ArrayList<>();
        int sizeResult = Math.min(start1.size(), end2.size());
        for (Integer item1 : start1) {
            for (Integer item2 : end2) {
                if (start1.get(item1) < sizeResult && end2.get(item2) < sizeResult) {
                    result.add(start1.get(item1));
                }
            }
        }
        return result.size();
    }
}

package com.example.demo.main.practice;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TheKWeakestRowsInAMatrixSample {
    public static void main(String[] args) {
        int[][] mat ={{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        System.out.println(Arrays.toString(kWeakestRows(mat, 3)));
    }

    static int[] kWeakestRows(int[][] mat, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) count++;
            }
            map.put(i, count);
        }
         map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

        return map.entrySet().stream()      .limit(k)
                .map(Map.Entry::getKey)
                .mapToInt(it -> it)
                .toArray();
    }
}

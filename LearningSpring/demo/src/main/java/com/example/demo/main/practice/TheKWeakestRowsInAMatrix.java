package com.example.demo.main.practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        int[][] mat = {{1,0},{1,0},{1,0},{1,1}};
        System.out.println(kWeakestRows(mat, 4));
    }

    static List<Integer> kWeakestRows(int[][] mat, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < mat.length; i++) {
            int total = 0;
            for (int j = 0; j < mat[i].length; j++) {
                System.out.println("position=" + i + "-" + j + " with value=" + mat[i][j]);
                total=total + mat[i][j];
            }
            map.put(i, total);
        }

        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(3)
                .map(Map.Entry::getKey)
                .mapToInt(item->item).boxed()
                .collect(Collectors.toList());
    }
}

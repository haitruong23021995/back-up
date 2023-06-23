package com.example.demo.main.internalCollection;

import java.util.*;
import java.util.Queue;

public class ListExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.add("Hello");
        queue.add("World");
//        System.out.println(queue);
        List<String> list = new ArrayList<String>();
//        list.set(0,"hai");
        list.add("ne6");
        list.add("ne2");
        list.add(null);
        list.add("ne4");
        list.add(null);
        list.add("ne6");
        list.add("ne7");
        list.add("ne8");
        list.add("ne9");
        list.add("ne10");

        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");

        list.remove("ne6");

        System.out.println(list);
//
//        List<String> list2 = new ArrayList<String>(list);
//        list2.add("123");
//        System.out.println(list2);
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("access_token", "accessToken2");
//        tokens.put("refresh_token", "refreshToken2");
//
//        list2 = new ArrayList<String>(tokens.keySet());
//        System.out.println(list2);

        List<Integer> list3 = new ArrayList<>(Arrays.asList(1,2));
        System.out.println(list3);

        List<String> list4 = new ArrayList<String>();
        list4.remove("a");
    }
}

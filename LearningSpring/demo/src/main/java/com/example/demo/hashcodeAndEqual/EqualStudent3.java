package com.example.demo.hashcodeAndEqual;

import java.util.*;

public class EqualStudent3 {
    public static void main(String[] args) {
        //TODO hashcode and equal: only use via key
        Student student1 = new Student("123", "Cong", "cong@gmail.com", 22);
        Student student2 = new Student("001", "Ga", "ga@gmail.com", 22);
        Student student3 = new Student("456", "Dung", "dung@gmail.com", 18);
        Student student4 = new Student("123", "Cong", "cong2@gmail.com", 22);

        Set<Student> setStudents = new HashSet<>();// output data not sequential and not duplicate data
        setStudents.add(student1);
        setStudents.add(student2);
        setStudents.add(student3);
        setStudents.add(student4);
        setStudents.add(null);

        // in các phần tử của set ra màn hình
        for (Student student : setStudents) {
            System.out.println(student);
        }

        System.out.println(-687212437& (16-1));

        LinkedList<String> stringList = new LinkedList<>();
        stringList.add("hai");
        stringList.add("ngoc");
        stringList.add("truong");
        stringList.add("hai1");
        stringList.add("hai2");
        stringList.add("hai3");
        stringList.get(3);//TODO need to search more
//        stringList.remove(0);//TODO need to search more
        System.out.println(stringList);

        List<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add("hai");
        arrayList.add("ngoc");
        arrayList.add("truong");
        arrayList.add("hai");
        System.out.println(arrayList);
        System.out.println(1<<4);
        System.out.println(1<<30);
        System.out.println(30>>2);
        System.out.println("----");

        Map<Student, Integer> hashMapStudent = new HashMap<>();// output data not sequential and not duplicate data
        hashMapStudent.put(student1, 1);
        hashMapStudent.put(student2, 2);
        hashMapStudent.put(student3, 3);
        hashMapStudent.put(student4, 4);
        hashMapStudent.put(null, null);

        System.out.println(hashMapStudent);

        HashMap map = new HashMap();
        map.put(new Key("vishal"), 20);
        map.put(new Key("sachin"), 30);
        map.put(new Key("vaibhav"), 40);

        System.out.println(map);
    }
}

package com.example.demo.java8;

interface Interface1 {
    default void doSomething() {
        System.out.println("Interface1 doSomething()");
    }
}

interface Interface2 {
    default void doSomething() {
        System.out.println("Interface2 doSomething()");
    }
}

public class MultiInheritance implements Interface1, Interface2, Shape {

    boolean isValid() {
        System.out.println("Vehicle is valid");
//        Shape.test = 5; can't change a final variable
        return true;
    }

    @Override
    public void doSomething() {
//        Override lại phương thức doSomething từ lớp con.
        Interface1.super.doSomething();
        Interface2.super.doSomething();
        //Gọi default method của một interface cụ thể bằng cách sử dụng từ khóa super.
        //doSomething(); call again
    }

    @Override
    public void draw() {

    }

    public static void main(String[] args) {
        MultiInheritance multiInheritance = new MultiInheritance();
        multiInheritance.doSomething();
        String a = "1";
//        int b = (int) a;
    }
}

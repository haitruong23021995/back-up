package com.example.demo.java8;

interface BaseInterface {
//    void base(); //FI can't have 2 abs methods
}

@FunctionalInterface
public interface DemoFunctionalInterface2 extends BaseInterface{

    public final static int age = 28;

    void doSomethingABC();

    int hashCode();

    String toString();

    boolean equals(Object obj);

    //can or can't override this method by the other class
    default void setColor(String color) {
        System.out.println("Draw shape with color " + color);
        isValid();
    }

    //can or can't override this method by the other class
    default void setColor2(String color) {
        System.out.println("Draw shape with color " + color);
        isValid();
    }

    //can't override this method by the other class, only call it if like
    static boolean isValid() {
        System.out.println("Vehicle is valid");
        return true;
    }
}

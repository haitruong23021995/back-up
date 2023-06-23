package com.example.demo.java8;

public interface Shape {

    public final int test = 0;

    void draw();

    //can or can't override this method by the other class
    default void setColor(String color) {
        System.out.println("Draw shape with color " + color);
        isValid();
    }

    //can't override this method by the other class, only call it if like
    static boolean isValid() {
        System.out.println("Vehicle is valid");
        return true;
    }

}

package com.example.demo.java8;

class Rectangle {
    private int width;
    private int height;

    public int calculateArea() {
        return this.width * this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    String test(){
        return null;
    }

}

class Square extends Rectangle {

    //TODO default (parent class)-> public (sub class) but still stay in the package
    @Override
    public String test() {
        return super.test();
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

public class LSPExample1 {
    public void example1() {
        Rectangle rect = new Rectangle();
        rect.setWidth(5);
        rect.setHeight(10);
        System.out.println(rect.calculateArea()); // 50

        Square square = new Square();
        square.setWidth(5);
        square.setHeight(10);
        System.out.println(square.calculateArea()); // 100
    }

    public static void main(String[] args) {
        LSPExample1 lspExample1 = new LSPExample1();
        lspExample1.example1();
    }
}

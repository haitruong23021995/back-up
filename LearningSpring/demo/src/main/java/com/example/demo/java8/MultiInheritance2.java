package com.example.demo.java8;

interface Interface3 {
    default void doSomething() {
        String x= "a";
        System.out.println("Execute in Interface3"+ x.toString());
    }

    static boolean isValid() {
        System.out.println("Vehicle is valid");
        return true;
    }
}

class Parent {
    public void doSomething() {
        System.out.println("Execute in Parent");
    }
}

abstract class ParentABS implements DemoFunctionalInterface2 {

    public ParentABS() {
    }

    abstract public String test();

    public void doSomething() {
        System.out.println("Execute in ParentABS");
    }
}

public class MultiInheritance2 extends Parent implements Interface3 {

    static int test =1;

    boolean isValid() {
        test =3;
        System.out.println("Vehicle is valid" + test);
        return true;
    }
    public void asd() {
        doSomething();
        Interface3.super.doSomething();
    }

    public static void main(String[] args) {
        test= 2;
        MultiInheritance2 m = new MultiInheritance2();
        m.isValid();
//        m.doSomething();
//        m.asd();
//        ParentABS parentABS = new ParentABS() {
//            @Override
//            public void doSomething() {
//                super.doSomething();
//            }
//        };
//        parentABS.doSomething();
//        Interface3.isValid();
        // Execute in Parent
        //can't call doSomething in here.
        // Require creating to override the doSomething method to use
    }

}

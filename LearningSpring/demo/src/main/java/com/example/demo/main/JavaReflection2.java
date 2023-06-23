package com.example.demo.main;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class JavaReflection2 {
    public static void main(String[] args) throws ClassNotFoundException {
        CustomerJavaReflection customer = new CustomerJavaReflection();
        customer.setName("kai");
        customer.setAge(25);
        demoReflection(customer);
    }

    public static void demoReflection(Object object) throws ClassNotFoundException {
        Class myClass = object.getClass();
//    Class myClass = Class.forName("stackjava.com.reflection.Customer");
        System.out.println("Class name: " + myClass.getName());
        System.out.println("Super Class name: " + myClass.getSuperclass().getName());
        System.out.println("Is interface: " + myClass.isInterface());
        System.out.println("Constructors: ");
        Constructor[] constructors = myClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("    Number of parameters: " + constructor.getParameterCount() + " - modifier: "
                    + getModifierName(constructor.getModifiers()));
        }
        System.out.println("Fields:");
        Field[] allFields = myClass.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println("    " + field.getName() + " - type: " + field.getType() + " - modifier: "
                    + getModifierName(field.getModifiers()));
        }
        System.out.println("Methods: ");
        Method[] methods = myClass.getDeclaredMethods();
        for (Method field : methods) {
            System.out.println("    " + field.getName() + " - modifier: " + getModifierName(field.getModifiers()));
        }
    }

    public static String getModifierName(int mod) {
        if (Modifier.isPrivate(mod)) {
            return "private";
        }
        if (Modifier.isProtected(mod)) {
            return "protected";
        }
        if (Modifier.isPublic(mod)) {
            return "public";
        }
        if (Modifier.isPrivate(mod)) {
            return "private";
        }
        return "default";
    }

}

@Data
class CustomerJavaReflection {
    private String name;
    private int age;
}
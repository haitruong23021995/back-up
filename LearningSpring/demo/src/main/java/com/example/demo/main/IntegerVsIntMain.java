package com.example.demo.main;

import java.math.BigDecimal;

public class IntegerVsIntMain {
    public static void main(String[] args) {
        String value = "123";
        Integer integer = Integer.valueOf(value);
        System.out.println(integer);

        System.out.println(new Integer(value));
        String bin = Integer.toBinaryString(123);
        String oct = Integer.toOctalString(123);
        String hex = Integer.toHexString(123);
        System.out.print(bin + "\n" + oct + "\n" + hex);

        decimalToBinary(123);

    }

    static void printBinary(int[] binary, int id) {
        String x = "";
        // Iteration over array
        for (int i = id - 1; i >= 0; i--) {
            System.out.print(binary[i]);
            x += binary[i];
        }
        //convert to binary to integer
        System.out.println("\n"+Integer.parseInt(x, 2));
    }

    // Function converting decimal to binary
    public static void decimalToBinary(int num) {
        // Creating and assigning binary array size
        int[] binary = new int[350];
        int id = 0;

        // Number should be positive
        while (num > 0) {
            System.out.println("id=" + id);
            binary[id++] = num % 2;
            num = num / 2;
        }

        // Print Binary
        printBinary(binary, id);
    }
}

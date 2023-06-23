package com.example.demo.main;

import java.util.stream.IntStream;

public class MultiThreadExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread main start");
        Thread thread1 = new Thread(() -> {
            IntStream.range(0, 11).forEach(item -> {
                try {
                    System.out.println("Thread One -> " + item);
                    Thread.sleep(000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            IntStream.range(0, 11).forEach(item -> {
                try {
                    System.out.println("Thread Two -> " + item);
                    Thread.sleep(000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        thread2.start();

        System.out.println("Thread 1 joins main");
        thread1.join();//after joined with main -> still runs un-synchronized with thread2
        // thread1 continues running to end
        // -> main continues running => wait for thread1 runs completed after runs main

        System.out.println("Thread 2 joins main");
        thread2.join();//after joined with main -> still runs un-synchronized with thread1
        //=> wait for thread2 runs completed after runs main

        System.out.println("Thread main stop");
    }
}

package com.example.demo.multithread;

public class VolatileCounterExample {
    private volatile static int MY_INT = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            int local_value = MY_INT;
            while (local_value < 5) {
                System.out.println("Thread B in while with local_value=" + local_value
                        + " MY_INT=" + MY_INT);
                if (local_value != MY_INT) {
                    System.out.println(local_value+"Thread B, Incrementing MY_INT to:" + MY_INT);
                    local_value = MY_INT;
                }
            }
        }).start();

        new Thread(() -> {
            int local_value = MY_INT;
            while (MY_INT < 5) {
                System.out.println("Thread A, Incrementing MY_INT to:" + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}

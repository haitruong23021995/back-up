package com.example.demo.multithread;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicExample {
    private AtomicBoolean active = new AtomicBoolean(false);

    public void prepare() throws InterruptedException {
        new Thread(() -> {
            System.out.println("application preparing ...");
            sleep(3);
            active.set(true);
        }).start();
    }

    public void start() throws Exception {
        new Thread(() -> {
            while(!active.get());
            System.out.println("application started");
        }).start();
    }

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        AtomicExample example = new AtomicExample();
        example.prepare();
        example.start();
        sleep(10);
    }
}

package com.example.demo.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

//SynchronizedCounterMain
@Slf4j
class AtomicCounter extends Thread {

    // Atomic counter Variable
    AtomicInteger count;

    // Constructor of class
    AtomicCounter() {
        count = new AtomicInteger();
    }

    // method which would be called upon
    // the start of execution of a thread
    public void run() {

        int max = 10000;

        // incrementing counter total of max times
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread name=" + Thread.currentThread().getName()
                    + " with item=" + i + " with count=" + count + ".");
            int value = count.addAndGet(1);
            log.info("value equals count={},value={}, Thread name={} item={}, count={}",
                    value == this.count.get(), value, Thread.currentThread().getName(),
                    i, this.count.get());
        }
    }
}

@Slf4j
public class AtomicCounterMain {
    public static void main(String[] args)
            throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // Instance of Counter Class
        AtomicCounter c = new AtomicCounter();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        // Threads start executing
        first.start();
        second.start();

        // main thread will wait for both
        // threads to complete execution
//        first.join();
//        second.join();

        // Printing final value of count variable
        System.out.println(c.count);
        long endTime = System.currentTimeMillis();

        log.info("time total={}", endTime - startTime);
    }
}

package com.example.demo.multithread;

import lombok.extern.slf4j.Slf4j;

//TODO take a look AtomicCounterMain
class Counter extends Thread {

    // Counter Variable
    int count = 0;

    // method which would be called upon
    // the start of execution of a thread
    public synchronized void run()
    {
        int max = 10000000;

        // incrementing counter total of max times
        for (int i = 0; i < max; i++) {
            count++;
            if (max - i == 1) {
                System.out.println(count);
            }
        }
    }
}

@Slf4j
public class SynchronizedCounterMain {
    public static void main(String[] args)
            throws InterruptedException
    {
        long startTime = System.currentTimeMillis();
        // Instance of Counter Class
        Counter c = new Counter();

        // Defining Two different threads
        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        // Threads start executing
        first.start();
        second.start();

        // main thread will wait for both
        // threads to complete execution
        first.join();
        second.join();

        // Printing final value of count variable
        System.out.println(c.count);
        long endTime = System.currentTimeMillis();

        log.info("time total={}", endTime - startTime);
    }
}

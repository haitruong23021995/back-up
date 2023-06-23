package com.example.demo.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

//TODO if CAS is failed, still tries to run in while loop until it's successful
@Slf4j
public class OptimisticLockCounter {
    private AtomicLong count = new AtomicLong();

    public void inc() {
        IntStream.range(0, 10000).forEach(item -> {
            log.info("Thread name={}, item ={}",
                    Thread.currentThread().getName(), item);
            boolean incSuccessful = false;
            while (!incSuccessful) {//back when incSuccessful == false
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("In while loop, Thread name={}, item={}, count={}",
                        Thread.currentThread().getName(), item, this.count);
                long value = this.count.get();
                long newValue = value + 1;

                incSuccessful = this.count.compareAndSet(value, newValue);
                log.info("incSuccessful={}, Thread name={} item={}, count={}",
                        incSuccessful, Thread.currentThread().getName(), item, this.count);
            }
        });
    }

    public long getCount() {
        return this.count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        OptimisticLockCounter optimisticLockCounter = new OptimisticLockCounter();
        Thread thread1 = new Thread(() -> {
            optimisticLockCounter.inc();
        });

        Thread thread2 = new Thread(() -> {
            optimisticLockCounter.inc();
        });

        long startTime = System.currentTimeMillis();

        thread1.start();
        thread2.start();
        // thread1.join();
        // thread2.join();
        System.out.println(optimisticLockCounter.getCount());
        long endTime = System.currentTimeMillis();

        log.info("time total={}", endTime - startTime);
    }
}

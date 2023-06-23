package com.example.demo.multithread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;


//TODO Data will show wrong cuz un-sync
@Slf4j
@Data
public class SyncThreadExample3 {

    private int acb;
    private int eximBank;

    private int flashRun;

    private int total() {
        return acb + eximBank;
    }

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        SyncThreadExample3 syncThreadExample = new SyncThreadExample3();
        syncThreadExample.setFlashRun(1);

        Thread thread1 = new Thread(() -> {
            synchronized (syncThreadExample) {
                IntStream.range(0, 11).forEach(item -> {
                    log.info("1--- item={}", item);
                    try {
                        if (syncThreadExample.getFlashRun() == 1) {
                            count.getAndIncrement();
                            int randomInt = new Random().nextInt(100);
                            syncThreadExample.setAcb(randomInt);
                            log.info(" ACB ={}", syncThreadExample.getAcb());

                            syncThreadExample.setFlashRun(2);
                            syncThreadExample.notifyAll();
                            Thread.sleep(100);
                        } else
                            syncThreadExample.wait(); //
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (syncThreadExample) {
                IntStream.range(0, 11).forEach(item -> {
                    log.info("2--- item={}", item);
                    try {
                        if (syncThreadExample.getFlashRun() == 2) {
                            count.getAndIncrement();
                            int randomInt = new Random().nextInt(100);
                            syncThreadExample.setEximBank(randomInt);
                            log.info(" EximBank ={}", syncThreadExample.getEximBank());

                            syncThreadExample.setFlashRun(3);
                            syncThreadExample.notifyAll();
                            Thread.sleep(100);
                        } else
                            syncThreadExample.wait(); //
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (syncThreadExample) {
                IntStream.range(0, 12).forEach(item -> {
                    log.info("3--- item={}", item);
                    try {
                        if (syncThreadExample.getFlashRun() == 3) {
                            count.getAndIncrement();
                            log.info(" Total ={}", syncThreadExample.total());

                            syncThreadExample.setFlashRun(1);
                            syncThreadExample.notifyAll();
                            Thread.sleep(100);
                        } else
                            syncThreadExample.wait(); //
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(count);
    }
}

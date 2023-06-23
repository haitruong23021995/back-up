package com.example.demo.multithread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.IntStream;


//TODO Data will show wrong cuz un-sync
@Slf4j
@Data
public class SyncThreadExample2 {

        private int acb;
        private int eximBank;
        private int scb;

        private int total() {
            return acb + eximBank;
        }

    public static void main(String[] args) {
        SyncThreadExample2 syncThreadExample = new SyncThreadExample2();

        Thread thread1 = new Thread( () -> {
            IntStream.range(0,11).forEach(item -> {
                int randomInt = new Random().nextInt(100);
                syncThreadExample.setAcb(randomInt);
                log.info(" ACB ={}",syncThreadExample.getAcb());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        });

        Thread thread2 = new Thread( () -> {
            IntStream.range(0,11).forEach(item -> {
                int randomInt = new Random().nextInt(100);
                syncThreadExample.setEximBank(randomInt);
                log.info(" EximBank ={}",syncThreadExample.getEximBank());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        });

        Thread thread3 = new Thread( () -> {
            IntStream.range(0,11).forEach(item -> {
                log.info(" Total ={}", syncThreadExample.total());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

package com.example.demo.multithread;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor
public class Thread1 extends Thread {

    private SharedData sharedData;

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(item -> {

            synchronized (sharedData) {
                log.info("item thread1 = {}", item);
                int rad = new Random().nextInt(100);
                sharedData.rad = rad;
                log.info("T1 > {} > {}", item, rad);

                sharedData.notifyAll();

                try {
                    if (item != 9) {
                        log.info("item thread1 when wait = {}", item);
                        sharedData.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

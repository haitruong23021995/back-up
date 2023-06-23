package com.example.demo.multithread;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor
public class Thread2 extends Thread {

    private SharedData sharedData;

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(item -> {

            synchronized (sharedData) {
                log.info("item thread2 = {}", item);
                sharedData.notifyAll();

                try {
                    //TODO add the below condition, first thread runs before and had rad,
                    // second thread won't run wait() and log the result
                    if (!(item == 0 && sharedData.rad > 0)) {
                        sharedData.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int result = sharedData.rad * sharedData.rad;
                log.info("T2 > {} > {}", item, result);
            }
        });
    }
}

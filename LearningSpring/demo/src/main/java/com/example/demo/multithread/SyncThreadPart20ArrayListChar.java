package com.example.demo.multithread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@Data
public class SyncThreadPart20ArrayListChar extends Thread {
    private ArrayList<Character> characterArrayList = new ArrayList<>();

    @Override
    public void run() {
        int min = 'a';
        int max = 'z';
        log.info(min + "--" + max);
        int limit = max - min;
        IntStream.range(0, 10).forEach(item -> {
            synchronized (this) {
            int randomChar = new Random().nextInt(limit) + min;
            characterArrayList.add((char) randomChar);
            if (characterArrayList.size() == 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }}
        });
    }
}

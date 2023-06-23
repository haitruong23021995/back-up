package com.example.demo.multithread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@Data
public class SyncThreadPart20ArrayListInt extends Thread {
    private ArrayList<Integer> integerArrayList = new ArrayList<>();

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(item -> {
            int randomInt = new Random().nextInt(100);
            integerArrayList.add(randomInt);
//            if (integerArrayList.size() == 10) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

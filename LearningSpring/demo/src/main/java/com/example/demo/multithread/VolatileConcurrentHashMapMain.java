package com.example.demo.multithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//TODO take a look AtomicConcurrentHashMapMain
public class VolatileConcurrentHashMapMain {
    private volatile int count;
    private final Map map = new ConcurrentHashMap();

    public void start() {
        Thread[] threads = new Thread[10];
        for (int i = 0 ; i < threads.length ; ++i) {
            threads[i] = new Thread(() -> {
                while(count <= 1000000) {
                    ++ count;
                    map.put(Integer.valueOf(count), Integer.valueOf(count));
                }
            });
        }
        for (int i = 0 ; i < threads.length ; ++i) {
            threads[i].start();
        }
    }

    public static void main(String[] args) throws Exception {
        VolatileConcurrentHashMapMain example = new VolatileConcurrentHashMapMain();
        example.start();
        Thread.sleep(3000);
        System.out.println(example.map.size());
    }
}

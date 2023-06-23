package com.example.demo.multithread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncThreadMainExample implements Runnable {

        private int total;

        public SyncThreadMainExample() {
            this.total = 1000;
        }

        public void validateWhenWithdrawn() throws InterruptedException {
            if (total > 0) {
                Thread.sleep(1000);//TODO why use it
                total = total - 1000;
                log.info(" withdrawn successfully ! {}", total);
            } else
                log.error(" no money to withdrawn ! {}", total);
        }

        @Override
        public void run() {
            try {
                validateWhenWithdrawn();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    public static void main(String[] args) {
        SyncThreadMainExample syncThreadMainExample = new SyncThreadMainExample();

        Thread thread1 = new Thread(syncThreadMainExample);
        Thread thread2 = new Thread(syncThreadMainExample);
        thread1.start();
        thread2.start();
    }
}

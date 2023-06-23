package com.example.demo.multithread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class MutableUserThreadMain {

    public MutableUser mutableUser;

    public static void main(String[] args) {
        MutableUserThreadMain mutableUserThreadMain = new MutableUserThreadMain(new MutableUser());
        Thread thread1 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            while (true) {
                String username = mutableUserThreadMain.getMutableUser().getName();
                log.info("Thread1 name={}", username);
            }
        });

        Thread thread2 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            mutableUserThreadMain.setMutableUser(null);
            log.info("Thread2 mutableUser={}", mutableUserThreadMain.getMutableUser());
        });

        thread1.start();
        thread2.start();
    }
}

@Data
@AllArgsConstructor
class MutableUser {
    private String name;
    private int age;

    public MutableUser() {
        name = "Loi";
        age = 26;
    }
}

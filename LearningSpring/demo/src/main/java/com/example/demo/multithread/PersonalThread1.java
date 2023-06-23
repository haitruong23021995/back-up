package com.example.demo.multithread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Data
@Slf4j
public class PersonalThread1 extends Thread {
    private List<Personal> personals;

    @Override
    public void run() {
        personals.forEach(item -> {
            synchronized (personals) {
                log.info("Name ={}", item.getName());
                personals.notifyAll();
                try {
                    if (personals.indexOf(item) != ((personals.size())- 1)) {
                        personals.wait();//
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    log.info("Index Of ={}", personals.indexOf(item));
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

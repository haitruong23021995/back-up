package com.example.demo.multithread;

import java.util.ArrayList;
import java.util.List;

public class PersonalMain {
    public static void main(String[] args) {
        List<Personal> personals = new ArrayList<>();
        personals.add(new Personal("a", "address 1"));
        personals.add(new Personal("b", "address 2"));
        personals.add(new Personal("c", "address 3"));
        personals.add(new Personal("d", "address 4"));
        PersonalThread1 personalThread1 = new PersonalThread1(personals);
        PersonalThread2 personalThread2 = new PersonalThread2(personals);

        personalThread1.start();
        personalThread2.start();
    }
}

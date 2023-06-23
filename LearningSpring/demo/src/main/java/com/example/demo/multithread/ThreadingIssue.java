package com.example.demo.multithread;

import lombok.AllArgsConstructor;

public class ThreadingIssue {
    public B b = new B();
}

class B {
    private final Object lock = new Object();

    public void someMethod(int timeOut) {
        synchronized (lock) {
            try {
                lock.wait(timeOut);
            } catch (Exception e) {
            }
            // some task..
            lock.notifyAll();
        }
    }
}

@AllArgsConstructor
class ThreadA1 extends Thread {
    private ThreadingIssue threadingIssue;

    @Override
    public void run() {
        while (true) {
            threadingIssue.b.someMethod(5000);
        }
    }
}

@AllArgsConstructor
class ThreadB2 extends Thread {
    private ThreadingIssue threadingIssue;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        threadingIssue.b = null;
    }

    public static void main(String[] args) {
        ThreadingIssue threadingIssue = new ThreadingIssue();
        ThreadA1 threadA1 = new ThreadA1(threadingIssue);
        ThreadB2 threadB2 = new ThreadB2(threadingIssue);

        threadA1.start();
        threadB2.start();
    }
}

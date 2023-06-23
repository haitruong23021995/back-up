package com.example.demo.multithread;

//TODO add the condition to stop thread item!=9
//thread1 and thread2 run async but when any thread run sync before,
// this thread will run before and the second thread must wait
//After the first thread runs notifyAll(), it wakes up all thread is sleeping because of wait()
//After the first thread runs wait(), the first thread is waited and
// the second thread will start running
public class SyncThreadSync1and2 {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        Thread thread1 = new Thread1(sharedData);
        Thread thread2 = new Thread2(sharedData);

        thread1.start();
        thread2.start();
    }
}

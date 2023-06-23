package com.example.demo.multithread;

public class MainSyncThread {
    public static void main(String[] args) {
        SyncThreadPart20ArrayListInt syncThreadPart20ArrayListInt =
                new SyncThreadPart20ArrayListInt();
        Thread th1 = new Thread(syncThreadPart20ArrayListInt);

        SyncThreadPart20ArrayListChar syncThreadPart20ArrayListChar =
                new SyncThreadPart20ArrayListChar();
        Thread th2 = new Thread(syncThreadPart20ArrayListChar);

        th1.start();

        th2.start();
    }
}

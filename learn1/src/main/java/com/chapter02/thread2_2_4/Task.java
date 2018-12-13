package com.chapter02.thread2_2_4;

public class Task {
    public void doLongTimeTask() {
        for (int i = 0; i < 20; i++) {
            System.out.println("NO SYNCHRONIZED THREAD NAME = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        System.out.println("*************************************************************************************************");
        synchronized (this) {
            for (int i = 0; i < 20; i++) {
                System.out.println("SYNCHRONIZED THREAD NAME = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }
}

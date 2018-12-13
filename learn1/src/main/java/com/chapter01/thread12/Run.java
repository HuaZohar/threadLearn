package com.chapter01.thread12;

public class Run {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
    }
}

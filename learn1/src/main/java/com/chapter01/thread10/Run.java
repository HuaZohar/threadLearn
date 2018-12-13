package com.chapter01.thread10;

public class Run {
    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(1000);
            myThread.suspend();
            System.out.println("main end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

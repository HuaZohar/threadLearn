package com.chapter01.thread3;

public class Run {
    public static void main(String[] args) {

        try {
            MyThread myThread = new MyThread();
            myThread.start();
            myThread.sleep(1000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}

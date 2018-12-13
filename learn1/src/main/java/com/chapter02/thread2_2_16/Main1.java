package com.chapter02.thread2_2_16;

public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();

        ThreadA a = new ThreadA(myService);
        a.setName("A");
        ThreadB b = new ThreadB(myService);
        b.setName("B");

        a.start();
        Thread.sleep(50);
        b.start();
    }
}

package com.chapter01.thread1;

public class CountOperate extends Thread {
    public CountOperate() {
        System.out.println("CountOperate begin");
        System.out.println("Thread.currentThread().getName()->" + Thread.currentThread().getName());
        System.out.println("this.getName()------------------->" + this.getName());
        System.out.println("CountOperate end");
        System.out.println("------------------------------------------------------------------------");
    }

    @Override
    public void run() {
        System.out.println("run begin");
        System.out.println("Thread.currentThread().getName()->" + Thread.currentThread().getName());
        System.out.println("this.getName()------------------->" + this.getName());
        System.out.println("run end");
    }
}

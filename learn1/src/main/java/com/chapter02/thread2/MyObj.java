package com.chapter02.thread2;

public class MyObj {
    synchronized public void methodA() {
        try {
            System.out.println("begin methodA , threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("methodA end, end time =" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB() {
        try {
            System.out.println("begin methodB , threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("methodB end, end time =" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

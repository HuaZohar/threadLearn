package com.chapter02.thread2_2_8_2;

public class MyObject {
    public void speedPrintString() {
        synchronized(this){
            System.out.println("speedPrintString , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
            System.out.println("***********************************");
            System.out.println("speedPrintString , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
        }

    }
}

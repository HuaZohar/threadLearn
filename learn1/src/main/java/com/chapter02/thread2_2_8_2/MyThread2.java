package com.chapter02.thread2_2_8_2;

public class MyThread2 extends Thread {
    private MyObject myObject;

    public MyThread2(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        myObject.speedPrintString();
    }
}

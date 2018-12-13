package com.chapter02.thread2_2_8_1;

public class MyThread2 extends Thread {
    private Service service;
    private MyObject myObject;

    public MyThread2(Service service, MyObject myObject) {
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        service.testMethod1(myObject);
    }
}

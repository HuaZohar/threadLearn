package com.chapter02.thread2_2_8_2;


public class MyThread1 extends Thread {
    private Service service;
    private MyObject myObject;

    public MyThread1(Service service, MyObject myObject) {
        this.service = service;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        service.testMethod1(myObject);
    }
}

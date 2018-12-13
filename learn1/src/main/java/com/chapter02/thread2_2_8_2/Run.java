package com.chapter02.thread2_2_8_2;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject myObject = new MyObject();

        MyThread1 myThread1 = new MyThread1(service, myObject);
        myThread1.setName("A");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(myObject);
        myThread2.setName("B");
        myThread2.start();
    }
}
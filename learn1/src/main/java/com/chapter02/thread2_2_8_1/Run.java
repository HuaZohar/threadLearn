package com.chapter02.thread2_2_8_1;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        MyObject myObject1 = new MyObject();

        MyThread1 myThread1 = new MyThread1(service, myObject1);
        myThread1.setName("A");
        myThread1.start();

        MyObject myObject2 = new MyObject();
        MyThread2 myThread2 = new MyThread2(service, myObject2);
        myThread2.setName("B");
        myThread2.start();
    }
}
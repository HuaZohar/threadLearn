package com.chapter01.thread2;

public class Run2 {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        System.out.println("begin = " + System.currentTimeMillis());
        myThread2.setName("AAA");
        myThread2.start();
        System.out.println("end = " + System.currentTimeMillis());


        System.out.println(Thread.currentThread().getName() + " (" + Thread.currentThread().getId() + ")");
        System.out.println(myThread2.getName() + " (" + myThread2.getId() + ")");


    }
}

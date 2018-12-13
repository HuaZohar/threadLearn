package com.chapter02.thread2_2_7_3;

public class Run {
    public static void main(String[] args) {
        MyOneList list = new MyOneList();

        MyThread1 myThread1 = new MyThread1(list);
        myThread1.setName("A");
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(list);
        myThread2.setName("B");
        myThread2.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("LIST SIZE : " + list.getSize());
    }
}

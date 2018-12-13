package com.chapter02.thread2_2_4;


public class Run {
    public static void main(String[] args) {
        Task task = new Task();
        MyThread1 myThread1 = new MyThread1(task);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2(task);
        myThread2.start();
    }
}

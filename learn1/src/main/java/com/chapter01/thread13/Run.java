package com.chapter01.thread13;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 myThread1 = new MyThread1();
        //设置优先级为 5-3
        myThread1.setPriority(Thread.NORM_PRIORITY - 3);
        myThread1.start();

        MyThread2 myThread2 = new MyThread2();
        //设置优先级为 5+3
        myThread2.setPriority(Thread.NORM_PRIORITY + 3);
        myThread2.start();

        Thread.sleep(20000);

        myThread1.stop();
        myThread2.stop();

        System.out.println("a = " + myThread1.getCount());
        System.out.println("b = " + myThread2.getCount());
    }
}

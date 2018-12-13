package com.chapter01.thread2;

public class Run1 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        System.out.println("begin = " + System.currentTimeMillis());
        myThread1.run();  //直接运行run方法 不开启线程
        //myThread1.start();
        System.out.println("end = " + System.currentTimeMillis());

    }
}

package com.chapter01.thread4;

public class Run {

    public static void main(String[] args) {


        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(1000);
            myThread.interrupt();

            System.out.println("是否停止1？ = " + Thread.interrupted());
            System.out.println("是否停止2？ = " + Thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.chapter01.thread7;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("在沉睡中停止！进入catch！" + this.isInterrupted());
            e.printStackTrace();
        }
    }
}

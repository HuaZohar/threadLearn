package com.chapter02.thread2_2_10;


public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.print("AA");
    }
}

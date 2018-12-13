package com.chapter02.thread2_2_7_1;

public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.setUsernamePassword("a", "aa");
    }
}

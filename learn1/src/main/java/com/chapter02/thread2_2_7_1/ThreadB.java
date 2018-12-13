package com.chapter02.thread2_2_7_1;

public class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.setUsernamePassword("b", "bb");
    }
}

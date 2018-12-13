package com.chapter02.thread2_2_5;

public class ThreadA extends Thread {
    private ObjectService objectService;

    public ThreadA(ObjectService objectService) {
        this.objectService = objectService;
    }

    @Override
    public void run() {
        super.run();
        objectService.serviceMethodA();
    }
}

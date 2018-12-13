package com.chapter02.thread2;

public class ThreadA extends Thread {
    private MyObj myObj;

    public ThreadA(MyObj myObj) {
        this.myObj = myObj;
    }

    @Override
    public void run() {
        super.run();
        myObj.methodA();
    }
}

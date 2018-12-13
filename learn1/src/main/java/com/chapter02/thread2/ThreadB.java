package com.chapter02.thread2;

public class ThreadB extends Thread {
    private MyObj myObj;

    public ThreadB(MyObj myObj) {
        this.myObj = myObj;
    }

    @Override
    public void run() {
        super.run();
        myObj.methodB();
    }
}

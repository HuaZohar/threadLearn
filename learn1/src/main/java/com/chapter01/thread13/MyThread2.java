package com.chapter01.thread13;

public class MyThread2 extends Thread {

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            count++;
        }
    }
}

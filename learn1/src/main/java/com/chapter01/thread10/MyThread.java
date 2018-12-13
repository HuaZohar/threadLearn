package com.chapter01.thread10;

public class MyThread extends Thread {
    private long i;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}

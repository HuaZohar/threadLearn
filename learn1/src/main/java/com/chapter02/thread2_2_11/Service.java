package com.chapter02.thread2_2_11;

public class Service {
    Object object1 = new Object();
    Object object2 = new Object();

    public void methodA() {
        synchronized (object1) {
            System.out.println("methodA BEGIN!");
            boolean isConRun = true;
            while (isConRun) {
            }
            System.out.println("methodA END!");
        }

    }

    public void methodB() {
        synchronized (object2) {
            System.out.println("methodB BEGIN!");
            System.out.println("methodB END!");
        }
    }
}

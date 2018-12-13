package com.chapter02.thread2_2_5;

public class ObjectService {
    public void serviceMethodA() {
        try {
            synchronized (this) {
                System.out.println("A BEGIN TIME = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A END TIME = " + System.currentTimeMillis());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void serviceMethodB() {
        synchronized (this) {
            System.out.println("B BEGIN TIME = " + System.currentTimeMillis());
            System.out.println("B END TIME = " + System.currentTimeMillis());
        }
    }
}

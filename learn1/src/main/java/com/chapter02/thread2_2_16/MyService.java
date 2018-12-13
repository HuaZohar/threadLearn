package com.chapter02.thread2_2_16;

public class MyService {
    private String lock = "123";

    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " , BEGIN TIME = " + System.currentTimeMillis());
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " , END TIME = " + System.currentTimeMillis());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

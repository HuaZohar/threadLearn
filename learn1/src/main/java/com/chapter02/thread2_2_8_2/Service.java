package com.chapter02.thread2_2_8_2;


public class Service {
    public void testMethod1(MyObject myObject) {
        synchronized (myObject) {
            try {
                System.out.println("testMethod1 , GET LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("testMethod1 , RELEASE LOCK TIME = " + System.currentTimeMillis() + " , RUN THREAD NAME = " + Thread.currentThread().getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

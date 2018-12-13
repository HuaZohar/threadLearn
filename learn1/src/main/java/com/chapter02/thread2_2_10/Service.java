package com.chapter02.thread2_2_10;

public class Service {
    public static void print(String strParam) {
        try {
            synchronized (strParam) {
                while (true) {
                    System.out.println("CURRENT THREAD NAME = " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

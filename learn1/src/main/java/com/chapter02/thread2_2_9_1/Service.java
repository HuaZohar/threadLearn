package com.chapter02.thread2_2_9_1;

public class Service {
/*    synchronized public static void printA() {
        try {
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printA");
            Thread.sleep(3000);
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printA");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printB");
        System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printB");
    }*/

    public static void printA() {
        synchronized (Service.class) {
            try {
                System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printA");
                Thread.sleep(3000);
                System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printA");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (Service.class) {
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " ENTER printB");
            System.out.println("THREAD NAME = " + Thread.currentThread().getName() + ", AT " + System.currentTimeMillis() + " OUT printB");
        }
    }

}

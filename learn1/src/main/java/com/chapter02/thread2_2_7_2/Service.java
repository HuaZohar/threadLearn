package com.chapter02.thread2_2_7_2;

public class Service {
    private String anyString = new String();

    public void a() {
        try {
            synchronized (anyString) {
                System.out.println("A BEGIN!");
                Thread.sleep(3000);
                System.out.println("A END");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void b() {
        System.out.println("B BEGIN!");
        System.out.println("B END!");
    }


}

package com.chapter02.thread2_2_12;

public class Run {
    public static void main(String[] args) {
        try {
            DeadThread d1 = new DeadThread();

            d1.setFlag("a");
            Thread thread1 = new Thread(d1);
            thread1.start();

            //Thread.sleep(100);

            d1.setFlag("b");
            Thread thread2 = new Thread(d1);
            thread2.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

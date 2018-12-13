package com.chapter01.thread12;

public class MyThread extends Thread {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("THREAD1 COST TIME ： " + (endTime - beginTime) + " MS");
    }
}

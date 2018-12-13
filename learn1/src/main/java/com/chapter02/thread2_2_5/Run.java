package com.chapter02.thread2_2_5;

public class Run {
    public static void main(String[] args) {
        ObjectService objectService = new ObjectService();

        ThreadA threadA = new ThreadA(objectService);
        threadA.setName("a");
        threadA.start();

        ThreadB threadB = new ThreadB(objectService);
        threadB.setName("b");
        threadB.start();
    }
}

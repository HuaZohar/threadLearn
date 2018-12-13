package com.chapter02.thread2_2_9_1;

public class Run {
    public static void main(String[] args) {
        Service service1 = new Service();
        Service service2 = new Service();

        ThreadA threadA = new ThreadA(service1);
        threadA.setName("A");
        threadA.start();

        ThreadB threadB = new ThreadB(service2);
        threadB.setName("B");
        threadB.start();
    }
}

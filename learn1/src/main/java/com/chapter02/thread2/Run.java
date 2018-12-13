package com.chapter02.thread2;

public class Run {
    public static void main(String[] args) {
        MyObj myObject = new MyObj();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("A");

        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("B");

        threadA.start();
        threadB.start();
    }
}

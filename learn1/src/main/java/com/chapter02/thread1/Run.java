package com.chapter02.thread1;

public class Run {
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNumA = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(hasSelfPrivateNumA);
        threadA.start();

        HasSelfPrivateNum hasSelfPrivateNumB = new HasSelfPrivateNum();
        ThreadB threadB = new ThreadB(hasSelfPrivateNumB);
        threadB.start();

    }
}

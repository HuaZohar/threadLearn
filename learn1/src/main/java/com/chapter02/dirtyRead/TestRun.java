package com.chapter02.dirtyRead;

public class TestRun {
    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            threadA.start();
            Thread.sleep(2000); //打印结果受此值的影响
            publicVar.getValue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

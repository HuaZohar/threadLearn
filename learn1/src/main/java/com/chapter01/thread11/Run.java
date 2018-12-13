package com.chapter01.thread11;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        //共享变量
        final MyObject myObject = new MyObject();

        //线程1
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                myObject.setValue("a", "aa");
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(500);

        //线程2
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                myObject.printUsernamePassword();
            }
        };
        thread2.start();
    }
}

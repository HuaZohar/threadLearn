package com.chapter02.thread2_2_7_3;

public class MyThread1 extends Thread {
    private MyOneList myOneList;

    public MyThread1(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "A");
    }
}

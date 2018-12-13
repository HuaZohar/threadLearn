package com.chapter02.thread2_2_7_3;

public class MyThread2 extends Thread {
    private MyOneList myOneList;

    public MyThread2(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "B");
    }
}

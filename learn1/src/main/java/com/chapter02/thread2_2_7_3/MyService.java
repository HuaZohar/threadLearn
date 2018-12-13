package com.chapter02.thread2_2_7_3;

public class MyService {
    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            synchronized (list){
                if (list.getSize() < 1) {
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

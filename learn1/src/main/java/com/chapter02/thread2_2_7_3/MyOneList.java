package com.chapter02.thread2_2_7_3;

import java.util.ArrayList;
import java.util.List;

public class MyOneList {
    private List list = new ArrayList();

    synchronized public void add(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }
}

package com.chapter02.thread2_2_1;

public class Task {
    private String getData1;
    private String getData2;

     public void doLongTimeTask() {
        try {
            System.out.println("BEGIN TASK!");

            Thread.sleep(3000);
            String Data1 = "COST LONG TIME DEAL TASK RETURN VALUE 1 threadName = " + Thread.currentThread().getName();
            String Data2 = "COST LONG TIME DEAL TASK RETURN VALUE 2 threadName = " + Thread.currentThread().getName();

            synchronized (this) {
                getData1 = Data1;
                getData2 = Data2;
            }

            System.out.println(getData1);
            System.out.println(getData2);

            System.out.println("END TASK!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

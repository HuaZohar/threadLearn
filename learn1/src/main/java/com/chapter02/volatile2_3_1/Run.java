package com.chapter02.volatile2_3_1;

public class Run {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("I WILL STOP IT! STOP THREAD NAME = " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}

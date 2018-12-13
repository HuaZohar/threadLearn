package com.chapter01.thread6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Run {
    public static void main(String[] args) {

        File f = new File("out.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);

        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        System.out.println("end!!");
    }
}

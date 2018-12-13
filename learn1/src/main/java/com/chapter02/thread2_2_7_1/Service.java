package com.chapter02.thread2_2_7_1;

public class Service {
    private String usernameParam;
    private String passwordParam;
    private String anyString = new String();

    public void setUsernamePassword(String username, String password) {
        try {
            synchronized (anyString) {
                System.out.println("THREAD NAME IS : " + Thread.currentThread().getName() + ", in " + System.currentTimeMillis() + " ENTER SYNCHRONIZED BLOCK!");
                usernameParam = username;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println("THREAD NAME IS : " + Thread.currentThread().getName() + ", in " + System.currentTimeMillis() + " OUT SYNCHRONIZED BLOCK!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

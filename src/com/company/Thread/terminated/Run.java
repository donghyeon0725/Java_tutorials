package com.company.Thread.terminated;

public class Run {
    public static void main(String[] args) {
        Work task = new Work();
        Thread thread = new Thread(task);
        thread.start();

        try {
            System.out.println("main 스레드 잠시 sleep");
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        task.stop();
        System.out.println("main 종료");

    }
}

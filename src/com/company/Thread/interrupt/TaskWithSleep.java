package com.company.Thread.interrupt;

public class TaskWithSleep implements Runnable {


    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("프로그램 실행 중");
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
        }

        System.out.println("자원 회수");
        System.out.println("실행 종료");
    }
}

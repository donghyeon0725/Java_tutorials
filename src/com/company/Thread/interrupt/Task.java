package com.company.Thread.interrupt;

public class Task implements Runnable {
    private boolean stop = false;

    public boolean stop() {
        return this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("프로그램이 실행 중입니다");

            if (Thread.interrupted())
                break;
        }
        System.out.println("자원 회수");
        System.out.println("실행 종료");

    }
}

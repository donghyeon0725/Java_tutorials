package com.company.Thread.terminated;

public class Work implements Runnable {
    private boolean stop = false;

    public boolean stop() {
        return this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("작업이 실행 중 입니다");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("자원 회수");
        System.out.println("실행 종료");
    }
}

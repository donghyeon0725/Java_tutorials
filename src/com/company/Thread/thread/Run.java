package com.company.Thread.thread;

public class Run {
    public static void main(String[] args) {
        Work work = new Work();
        work.start();

        Thread newWork = new Thread() {
            // 작업을 적습니다.
        };
        newWork.start();
    }
}

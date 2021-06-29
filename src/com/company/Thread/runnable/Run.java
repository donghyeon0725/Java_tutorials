package com.company.Thread.runnable;

public class Run {
    public static void main(String[] args) {
        Runnable runnable = new Sample();
        Thread thread = new Thread(runnable);
        thread.start();


        Thread threadWithAnanymous = new Thread(() -> {
            // Run 에 해당하는 코드 흐름을 넣어줍니다.
        });
        threadWithAnanymous.start();
    }
}

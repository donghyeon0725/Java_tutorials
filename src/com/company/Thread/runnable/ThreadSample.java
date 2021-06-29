package com.company.Thread.runnable;

import java.awt.*;

public class ThreadSample {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i=0; i<5; i++) {
                toolkit.beep();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }
        });
        thread.start();

        for (int i=0; i<5; i++) {
            System.out.println("출력");
            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
    }
}

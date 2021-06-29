package com.company.Thread.demon;

public class Player implements Runnable {
    private boolean stop = false;

    class Demon implements Runnable {
        private boolean flag = true;
        @Override
        public void run() {
            while (flag) {
                System.out.println("데몬 스레드 실행 중");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("데몬 스레드가 종료 됩니다.");
        }
    }

    public boolean stop() {
        return this.stop = true;
    }

    @Override
    public void run() {
        Thread thread = new Thread(new Demon());
        thread.setDaemon(true);
        thread.start();

        while (!stop) {
            System.out.println("프로그램이 실행 중입니다");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("프로그램이 종료 됩니다");
    }
}

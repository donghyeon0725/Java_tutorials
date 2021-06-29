package com.company.Thread.demon;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Player player = new Player();
        Thread thread = new Thread(player);
        thread.start();

        Thread.sleep(100);

        player.stop();
    }
}

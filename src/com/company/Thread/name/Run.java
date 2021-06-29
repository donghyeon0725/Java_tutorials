package com.company.Thread.name;

public class Run {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("thread1");
        });

        System.out.println(thread1.getName());
        System.out.println(thread2.getName());

        thread1.setName("새로운 이름 1");
        thread2.setName("새로운 이름 2");

        System.out.println(thread1.getName());
        System.out.println(thread2.getName());
    }
}

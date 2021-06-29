package com.company.Thread.multi;

public class Run {
    public static void main(String[] args) {
        Calculator noneSynchronizedCalculator = new NoneSynchronizedCalculator();
        Calculator synchronizedCalculator = new SynchronizedCalculator();

        /*
        calculator 객체를 공유하기 때문에
        user1 은 calculator 값을 200 으로,
        user2 은 calculator 값을 100 으로 각각 다르게 설정했지만,
        2초 뒤 출력된 최종 내용은 100으로 동일 합니다.
        */
        User user1 = new User(noneSynchronizedCalculator, 200);
        User user2 = new User(noneSynchronizedCalculator, 100);

        Thread thread1 = new Thread(user1);
        Thread thread2 = new Thread(user2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        User newUser1 = new User(synchronizedCalculator, 200);
        User newUser2 = new User(synchronizedCalculator, 100);

        Thread newThread1 = new Thread(newUser1);
        Thread newThread2 = new Thread(newUser2);

        newThread1.start();
        newThread2.start();
    }
}

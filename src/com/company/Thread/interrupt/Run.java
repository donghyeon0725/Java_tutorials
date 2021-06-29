package com.company.Thread.interrupt;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        // Thread Sleep 상태에서 예외를 발생 시키면 실행 대기 상태가 되는데 이를 이용한 종료 방법
        // => interrupt 자체가 예외를 발생시키는 것이 아니라, sleep 메소드가 호출 되었는데 정지 상태가 되지 않으면 Exception이 발생하는 것이다.
        // 즉, sleep 이 호출되지 않으면 종료 시킬 수 없음
        Runnable task = new TaskWithSleep();
        Thread thread = new Thread(task);
        thread.start();
        thread.interrupt();



        Thread.sleep(100);



        System.out.println("interrupt 호출로 종료시키는 방법 (계속 진행 시켜서 종료)");
        // Thread 종료를 위한 메소드도 지원하고, interrupt 호출 여부를 검사해서 종료하는 방법
        Runnable newTask1 = new Task();
        Thread newThread1 = new Thread(newTask1);
        newThread1.start();
        newThread1.interrupt();



        Thread.sleep(100);



        System.out.println("stop 메소드로 종료 시키는 방법");
        Task newTask2 = new Task();
        Thread newThread2 = new Thread(newTask2);
        newThread2.start();
        Thread.sleep(1);
        newTask2.stop();
    }
}

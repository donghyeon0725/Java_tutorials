package com.company.Thread.local;

public class Run {
    public static void main(String[] args) {
        // 저장소를 공유 합니다.
        Repository repository = new Repository();

        Thread thread1 = new Thread(() -> {
            repository.setMemory(1);
            repository.setIntoThreadLocal(1);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("memory : " + repository.getMemory());
            System.out.println("threadLocal : " + repository.getFromThreadLocal());
        });

        Thread thread2 = new Thread(() -> {
            repository.setMemory(2);
            repository.setIntoThreadLocal(2);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("memory : " + repository.getMemory());
            System.out.println("threadLocal : " + repository.getFromThreadLocal());
        });


        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("memory : " + repository.getMemory());
        System.out.println("threadLocal : " + repository.getFromThreadLocal());


        /*
        출력 결과
        memory : 2 // thread2
        memory : 2 // main
        memory : 2 // thread1
        threadLocal : 2 // thread2
        threadLocal : null // main
        threadLocal : 1 // thread1
        */
    }
}

class Repository {
    private Integer memory;

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void setMemory(Integer value) {
        this.memory = value;
    }

    public Integer getMemory() {
        return this.memory;
    }

    public void setIntoThreadLocal(Integer value) {
        threadLocal.set(value);
    }

    public Integer getFromThreadLocal() {
        return threadLocal.get();
    }
}

package com.company.Thread.multi;

public class SynchronizedCalculator implements Calculator {
    private int memory;

    @Override
    public int getMemory() {
        return memory;
    }

    @Override
    public synchronized Calculator setMemory(int memory) {
        this.memory = memory;

        try { Thread.sleep(2000); }
        catch(InterruptedException e) {}

        System.out.println(this.memory);

        return this;
    }
}

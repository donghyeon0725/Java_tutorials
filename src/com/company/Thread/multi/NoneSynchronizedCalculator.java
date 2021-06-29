package com.company.Thread.multi;

public class NoneSynchronizedCalculator implements Calculator {
    private int memory;

    @Override
    public int getMemory() {
        return memory;
    }

    @Override
    public Calculator setMemory(int memory) {
        this.memory = memory;

        try { Thread.sleep(2000); }
        catch(InterruptedException e) {}

        System.out.println(this.memory);

        return this;
    }
}

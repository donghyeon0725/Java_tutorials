package com.company.Thread.multi;

public class User implements Runnable {
    private Calculator calculator;
    private int value;

    public User(Calculator calculator, int value) {
        this.calculator = calculator;
        this.value = value;
    }

    @Override
    public void run() {
        calculator.setMemory(this.value);
    }
}

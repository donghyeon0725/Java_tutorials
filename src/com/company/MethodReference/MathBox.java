package com.company.MethodReference;

public class MathBox {
    private int instance_value;

    public MathBox() {}

    public MathBox(int value) {
        this.instance_value = value;
    }

    public int sum(int a, int b) {
        return a + b;
    }

   public int sub(int a, int b) {
        return a - b;
   }

   public static int div(int a, int b) {
        return a / b;
   }

   public static int mul (int a, int b) {
        return a * b;
   }

   public int getSumWithInstance(int a, int b) {
        return this.instance_value + a + b;
   }
}

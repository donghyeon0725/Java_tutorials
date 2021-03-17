package com.company.MethodReference;

public class Run1 {
    public static void main(String[] args) {
        MathInterface i = Math::random;
        System.out.println(i.get());
    }
}

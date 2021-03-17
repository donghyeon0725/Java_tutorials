package com.company.MethodReference;

public class Run5 {
    public static void main(String[] args) {
        MathBox box = new MathBox();

        Function sum1 = box::sum;
        // 람다 표현식
        Function sum2 = (int a, int b) -> a+b;
        // 익명 클래스로 인터페이스 생성
        Function sum3 = new Function() {
            @Override
            public int func(int a, int b) {
                return a+b;
            }
        };

        System.out.println(sum1.func(5,5)); // 10
        System.out.println(sum2.func(5,5)); // 10
        System.out.println(sum3.func(5,5)); // 10

    }
}

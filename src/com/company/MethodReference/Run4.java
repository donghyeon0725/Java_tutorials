package com.company.MethodReference;

public class Run4 {
    public static void main(String[] args) {
        // 박스 1 생성. 이 때 생성자를 통해 내부적으로 instance_value 값으로 20을 가짐
        MathBox box1 = new MathBox(20);
        MathBox box2 = new MathBox(0);

        // 함수형 인터페이스를 통해 받았음
        Function sum1 = box1::getSumWithInstance;
        Function sum2 = box2::getSumWithInstance;

        // 같은 5 + 5 를 수행함
        System.out.println(sum1.func(5,5)); // 30
        System.out.println(sum2.func(5,5)); // 10

    }
}

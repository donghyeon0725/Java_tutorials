package com.company.MethodReference;

public class Run3 {
    public static void main(String[] args) {
        Function mul = MathBox::mul;

        Number number = new Number(1, 5);
        // 인터페이스를 넘기나 인터페이스의 함수 참조값을 넘기나 동일하다.
        number.print(mul);          // 5
        number.print(mul::func);    // 5

    }
}

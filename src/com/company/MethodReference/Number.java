package com.company.MethodReference;

public class Number {
    private int a;
    private int b;

    public Number(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /**
     * 인터페이스 Function 를 받아 내부의 함수를 호출하여 얻은 값을 출력한다.
     * */
    public void print(Function func) {
        System.out.println(func.func(this.a, this.b));
    }

    public int sum() {
        return this.a + this.b;
    }
}

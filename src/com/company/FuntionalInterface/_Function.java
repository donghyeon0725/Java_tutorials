package com.company.FuntionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    /**
     * 첫 제네릭은 입력값의 타입
     * 두번째 제네릭은 출력값의 타입이다.
     */
    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    /**
     * 위와 아래는 동일한 함수이다.
     */
    static int incrementByOne(int number) {
        return number + 1;
    }

    /**
     * 위 건 테스트
     */
    static void functionalInterface() {
        int increment1 = incrementByOne(0);
        System.out.println(increment1);

        /**
         * apply 를 통해서 함수를 호출할 수 있다.
         * */
        int increment2 = incrementByOneFunction.apply(increment1);
        System.out.println(increment2);
    }

    /**
     * 곱셈을 해주는 함수형 인터페이스
     * */
    static Function<Integer, Integer> multipleBy10Function = number -> number * 10;

    /**
     * 함수형 인터페이스를 두개 이어 붙이는 방법
     * */
    static void functionalInterfaceWithAndThen() {
        Function<Integer, Integer> addBy1AndThenMultiplyBy10 = incrementByOneFunction.andThen(multipleBy10Function);
        System.out.println(addBy1AndThenMultiplyBy10.apply(1));
    }

    /**
     * 인자를 2개 가지는 함수형 인터페이스 생성
     * 제네릭은 매개변수1, 매개변수2, 리턴타입 순서이다.
     * */
    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyFunction = (number, numToMultiplyBy) -> (number+1) * numToMultiplyBy;

    /**
     * 위와 동일한 코드이다.
     * */
    static int incrementByOneAndMultiply(int number, int numToMultiplyBy) {
        return (number+1) * numToMultiplyBy;
    }

    /**
     * 인자를 두개 가지는 함수형 인터페이스 사용
     * */
    static void functionalInterfaceWithTwoParams() {
        int result = incrementByOneAndMultiplyFunction.apply(5, 10);
        System.out.println(result);
    }




}

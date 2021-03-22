package com.company.FuntionalInterface;

import java.util.function.Predicate;

public class _Predicate {

    /**
     * 매개변수로 String을 받아 boolean 타입을 리턴하는 함수형 인터페이스
     * */
    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber -> phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    /**
     * 위와 동일한 메소드
     * */
    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }

    /**
     * predicate 사용 방법
     * */
    static void predicateTest() {
        System.out.println(isPhoneNumberValid("07000000000"));
        System.out.println(isPhoneNumberValidPredicate.test("07000000000"));
    }

    /**
     * 3이 포함 되어 있는 값인지 확인
     * */
    static Predicate<String> containsNumber3 = phoneNummber -> phoneNummber.contains("3");

    /**
     * predicate 함수를 두개 이어 붙이는 방법
     * 모든 이어 붙인 인터페이스가 true를 리턴해야만 true임
     * */
    static void predicateAndTest() {
        Predicate<String> validCheckAndContains3 = isPhoneNumberValidPredicate.and(containsNumber3);
        System.out.println(validCheckAndContains3.test("07000000030"));
    }



}

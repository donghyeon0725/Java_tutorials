package com.company.MethodReference;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run7 {
    public static void main(String[] args) {

        List<Object> simpleList = Arrays.asList(
                new MemberVO(), // true
                new UserVO()    // false
        );

        // MemberVO::isClassOf MemberVO 의 클래스가 맞는지 판별
        simpleList.stream()
                .map(MemberVO::isClassOf)
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
}

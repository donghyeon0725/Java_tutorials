package com.company.MethodReference;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Run6 {
    public static void main(String[] args) {
        // 인자로 넘겨준 객체를 리스트로 만들어 준다.
        List<String> stringList1 = Arrays.asList("01","02","03");
        
        // stringList 으로 부터 stream 을 뽑아낸다.
        stringList1.stream()
                // Integer::parseInt 를 참조한다. 이 때 map 이라는 매개변수로 넘어온 메소드를 A라고 하면 A의 메소드의 매개변수로 1,2,3(문자열)을 넘겨준다.
                .map(Integer::parseInt)
                // 다시 리스트로 가공한다.
                .collect(Collectors.toList())
                // 리스트의 문자열을 반복적으로 출력한다.
                .forEach(System.out::println);


        List<String> stringList2 = Arrays.asList("01","02","03");

        stringList2.stream()
                // 람다로 작성해도 똑같다.
                .map(e->Integer.parseInt(e))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}

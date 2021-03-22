package com.company.FuntionalInterface;

import java.util.*;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Imperative {

    public static void main(String[] args) {
        // 리스트 생성
        List<Person> people = Arrays.asList(
            new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Aisha", Gender.MALE),
            new Person("Alex", Gender.FEMALE),
            new Person("Alice", Gender.MALE)
        );

        // Imperative approach => 직접 리스트를 반복하는 행위 (우리가 index를 제어함)
        List<Person> females = new ArrayList<>();

        // 객체를 구분하기 위함
        for (Person person : people) {
            if (Gender.FEMALE.equals(person.gender)) {
                females.add(person);
            }
        }

        for (Person female : females) {
            System.out.println(female);
        }


        // 이 복잡한 과정을 함수형 프로그래밍으로 줄일 수 있을 거 같음
        List<Person> males = people.stream()
                .filter(person -> Gender.MALE.equals(person.gender))
                .collect(Collectors.toList());
        //males.forEach(e -> System.out.println());
        // 메소드를 참조만 하는 문법. 실행하는 것이 아닌, 참조만 하는 것이기에 인자를 넘겨주지 않는다.
        // 인자가 있으면 인자를 넘겨줄 것이고
        males.forEach(System.out::println);

        /**
         * 다만 이와 같이 ::new 로 넘겨줄 경우
         * 단순히 메소드를 참조하는 것이 아닌, 새로 만든다는 특징이 있음
         * */
        List<String> strings = Arrays.asList(
                "a",
                "b",
                "c"
        );

        // 특정 인스턴스의 메소드를 참조하게도 할 수 있다.
        strings.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
        strings.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println); // 요소들이 각각 참조할 함수를 넘겨줄 수 있다.
        // 또는 새로운
        strings.stream().map(String::new).collect(Collectors.toList()).forEach(System.out::println);


        IntBinaryOperator test = (a, b)-> 3;
        Predicate<Person> personPredicate = person -> Gender.MALE.equals(person.gender);

        // Declarative approach => 언어적인 레벨에서 반복문을 돌려주고 있음. 우리는 index 에 대해 신경쓸 필요가 없다.
        people.stream()
                .filter(personPredicate)
                .collect(Collectors.toList())
                .forEach(System.out::println);




    }

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name) &&
                    gender == person.gender;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, gender);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE
    }
}

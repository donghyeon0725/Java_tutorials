package com.company.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Collectors;

public class _Stream {

    public static void main(String[] args) {
        // 리스트 생성
        List<Person> people = Arrays.asList(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.MALE),
                new Person("Alex", Gender.FEMALE),
                new Person("Alice", Gender.MALE)
        );

        System.out.println("List으로 변환하는 방법");
        /**
         * List으로 변환하는 방법
         * */
        people.stream()
                .map(person -> person.gender.equals(Gender.MALE)) // true인지 false인지를 별도의 스트림으로 만든다.
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("=============");



        System.out.println("Set으로 변환하는 방법");
        /**
         * Set으로 변환하는 방법
         * */
        people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet()) // 중복을 제거하였다.
                .forEach(System.out::println);

        System.out.println("=============");

        System.out.println("중복제거 용도로 아주 유용하다. ");
        /**
         * 중복제거 용도로 아주 유용하다.
         * */
        people.stream()
                .map(person -> person.name)
                .collect(Collectors.toSet()) // 중복을 제거하였다.
                .forEach(System.out::println);



        /**
         * 각각의 함수 참조를 별도의 인터페이스로 받아둘 수 있다.
         * */
        Function<Person, String> returnPersonsName = person -> person.name;
        // 함수의 참조로 받는 것을 대신한다.
        ToIntFunction<String> getLength = String::length;
        IntConsumer println = System.out::println;


        System.out.println("=============");
        System.out.println("길이를 int로 변환한 리스트를 얻는다.");

        /**
         * 길이를 int로 변환한 리스트를 얻는다.
         * */
        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length)
                .forEach(System.out::println);


        System.out.println("=============");
        System.out.println("위와 같은 동작을 인터페이스를 이용해서 변경했다.");

        /**
         * 위와 같은 동작을 인터페이스를 이용해서 변경했다.
         * */
        people.stream()
                .map(returnPersonsName)
                .mapToInt(getLength)
                .forEach(println);


        System.out.println("=============");
        System.out.println("containOnlyFemails ?");


        /**
         * 여성인지 여부 확인
         * */
        Predicate<Person> isFemale = person -> Gender.FEMALE.equals(person.gender);

        /**
         * 모든 리스트가 조건에 부합하는지를 확인한다.
         * */
        boolean containOnlyFemales = people.stream()
                .allMatch(isFemale);

        System.out.println(containOnlyFemales);


        System.out.println("=============");
        System.out.println("containAnyFemails ?");

        /**
         * 최소 한개의 리스트가 조건에 부합하는지를 확인한다.
         * */
        boolean containAnyFemales = people.stream()
                .anyMatch(isFemale);

        System.out.println(containAnyFemales);


        System.out.println("=============");
        System.out.println("containNoneFemails ?");

        boolean containNoneFemales = people.stream()
                .noneMatch(isFemale);

        System.out.println(containNoneFemales);

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

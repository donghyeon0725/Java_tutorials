package com.company.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class _Stream_Collector {

    public static void main(String[] args) {
        Animal animal = new Animal(1, "동물 1");
        List<Animal> animals = Arrays.asList(animal, animal, new Animal(2, "동물 2"));

        for (Animal m : animals) {
            System.out.println(m.id + ", " + m.name);
        }
        System.out.println("-------------------------------------------");
        /*
        1, 동물 1
        1, 동물 1
        2, 동물 2
        * */

        List<Integer> animalIds = animals.stream().map(Animal::getId).collect(Collectors.toList());

        for (Integer id : animalIds) {
            System.out.println(id);
        }
        System.out.println("-------------------------------------------");
        /*
        1
        1
        2
        * */


        List<String> animalStrs = animals.stream().map(Animal::getName).collect(Collectors.toList());

        for (String name : animalStrs) {
            System.out.println(name);
        }
        System.out.println("-------------------------------------------");
        /*
        동물 1
        동물 1
        동물 2
        * */


        // 중복 제거가 되는 것이 특징
        Set<Animal> animalSet = animals.stream().collect(Collectors.toSet());

        for (Animal m : animalSet) {
            System.out.println(m.id + ", " + m.name);
        }
        System.out.println("-------------------------------------------");
        /*
        2, 동물 2
        1, 동물 1
        * */


    // 키 충돌이 날 경우 어떤 것을 사용할지 3번째 인자로 넘겨준다. 같은 키가 있을 경우 나중 것을 사용
    Map<Integer, Animal> animalMap = animals.stream().collect(Collectors.toMap(o -> o.getId(), o -> o, (old, later) -> later));

    for (Integer key : animalMap.keySet()) {
        System.out.println("key : " + key + ", " + animalMap.get(key).getId());
    }
    System.out.println("-------------------------------------------");
    /*
    key : 1, 1
    key : 2, 2
    * */


        // 같은 키로 묶여서 Map 으로 만듬
        Map<Integer, List<Animal>> animalMapGroupBy = animals.stream().collect(Collectors.groupingBy(o -> o.getId()));

        for (Integer key : animalMapGroupBy.keySet()) {
            System.out.println("key : " + key + "[");

            for (Animal m : animalMapGroupBy.get(key)) {
                System.out.println(m.id + ", " + m.name);
            }
            System.out.println("]");
        }
        System.out.println("-------------------------------------------");
        /*
        key : 1 [
            1, 동물 1
            1, 동물 1
        ]
        key : 2 [
            2, 동물 2
        ]
        * */

    }

    static class Animal {
        private Integer id;
        private String name;

        public Animal(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}

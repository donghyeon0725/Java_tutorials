package com.company.Abstract_Interface_Lamda;

public class Run {
    public static void main(String[] args) {
        /**
         * 아래의 경우 생성자로 생성한 값에 따라서 존재가 달라지게 하고 싶다. 즉, 생성할 때의 생성자(조건)에 따라 존재가 달라짐
         * */
        User user = new User("kim");
        String introduce1 = user.is(s -> {
            if ("kim".equals(s)) return s + " is people";
            if ("mong".equals(s)) return s + " is dog";
            return "is nothing";
        });
        System.out.println(introduce1);


        /**
         * 아래와 같이 인터페이스를 'new + 익명클래스'로 즉시 생성해서 넘겨주는 방법도 있긴 하나, 큰 의미가 없다.
         * */
        Interface inter = new Interface(){
            @Override
            public String identity(String s) {
                return "this is nothing";
            }
        };
        /**
         * 또는 람다로 생성하는 방법도 있다.
         * 이는 함수를 넘겨주는 것과 같다.
         * */
        inter = s -> s + " is interface";
        String introduce2 = user.is(inter);
        System.out.println(introduce2);


        /**
         * 추상 클래스의 경우. 인터페이스 처럼 람다의 사용은 불가능하다. 익명 클래스 또는 해당 추상클래스를 상속 받은 클래스의 인스턴스를 넘겨 받음으로서 실행이 가능하다.
         * */
        Animal animal = new Animal("wang");
        /*
        String introduce2 = animal.is(s -> {
            if ("kim".equals(s)) return s + " is people";
            if ("mong".equals(s)) return s + " is dog";
            return " is nothing";
        });
        */
        Abstract ac = new Abstract() {
            @Override
            String identity(String s) {
                if ("wang".equals(s)) return s + " is cat";
                if ("mong".equals(s)) return s + " is dog";
                return "is nothing";
            }
        };
        String introduce3 = animal.is(ac);
        System.out.println(introduce3);

    }
}

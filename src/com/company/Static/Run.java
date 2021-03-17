package com.company.Static;

public class Run {
    public static void main(String[] args) {
        Static s1 = new Static();
        Static s2 = new Static();
        Static s3 = new Static();
        Static s4 = new Static();

        // 스태틱 메소드는 오직 클래스틑 통해서만 접근 가능
        System.out.println("Static : " + Static.getStaticStrWithStatic());

        System.out.println("s1 static field : " + s1.getStaticStr());
        System.out.println("s2 static field : " + s2.getStaticStr());
        System.out.println("s3 static field : " + s3.getStaticStr());
        System.out.println("s4 static field : " + s4.getStaticStr());
        System.out.println("=======================");

        // 스태틱 변수는 인스턴스로 변경하던, 클래스를 통해 변경하던 모든 인스턴스의 값이 변경된다.
        s2.setStaticStr("change");

        System.out.println("Static : " + Static.getStaticStrWithStatic());

        System.out.println("s1 static field : " + s1.getStaticStr());
        System.out.println("s2 static field : " + s2.getStaticStr());
        System.out.println("s3 static field : " + s3.getStaticStr());
        System.out.println("s4 static field : " + s4.getStaticStr());
        System.out.println("=======================");

        // 스태틱 메소드로 스태틱 필드를 변경해도 적용됨
        Static.setStaticStrWithStatic("change with static method");

        System.out.println("Static : " + Static.getStaticStrWithStatic());

        System.out.println("s1 static field : " + s1.getStaticStr());
        System.out.println("s2 static field : " + s2.getStaticStr());
        System.out.println("s3 static field : " + s3.getStaticStr());
        System.out.println("s4 static field : " + s4.getStaticStr());


    }
}

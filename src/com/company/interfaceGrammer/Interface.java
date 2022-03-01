package com.company.interfaceGrammer;

public interface Interface {
    String run(String tvName);

    /**
     * default 메소드는 구현체를 가질 수 있습니다.
     * */
    default void defaultMethod() {
        System.out.println("default method");
        privateMethod();
    }

    /**
     * 자바 11 부터 접근제한자가 private 인 경우 메소드 구현체를 가질 수 있게 되었습니다.
     * */
    private void privateMethod() {
        System.out.println("privateMethod");
    }

    static String hi() {
        return "asd";
    }

    /**
     * static 메소드의 경우 메소드 구현체를 가질 수 있습니다.
     * 이 때, Interface 의 구현체를 리턴할 수 있는데 인터페이스의 메소드가 1개이 경우 람다를 통해서 구현체를 리턴할 수 있습니다.
     * */
    static Interface getInterfaceImpl(Integer channelNumber, String programName) {
//        return new Interface() {
//            @Override
//            public String run(String tvName) {
//                return channelNumber + tvName + programName;
//            }
//        };

        // 위와 완전히 동일합니다. 인터페이스의 메소드가 2개 이상인 경우 익명클래스를 리턴하여야 합니다.
        return (tvName) -> channelNumber + tvName + programName;
    }
}

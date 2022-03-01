package com.company.interfaceGrammer;

public class Client {
    public static void main(String[] args) {
        Interface anInterface = new Interface() {
            @Override
            public String run(String tvName) {
                return tvName;
            }
        };

        System.out.println(anInterface.run("티비입니다!"));

        anInterface.defaultMethod();
        Interface newInterface = Interface.getInterfaceImpl(1, "티비특공대");
        System.out.println(newInterface.run("/나의티비/"));;
    }
}

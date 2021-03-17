package com.company.Abstract_Interface_Lamda;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    /**
     * 받은 추상 클래스에 생성자를 넘겨준다.
     * */
    public String is(Abstract c) {
        return c.identity(this.name);
    }
}

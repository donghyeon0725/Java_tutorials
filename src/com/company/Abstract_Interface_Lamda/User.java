package com.company.Abstract_Interface_Lamda;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    /**
     * 받은 인터페이스에 생성자를 넘겨준다.
     * */
    public String is(Interface c) {
        return c.identity(this.name);
    }
}

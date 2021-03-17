package com.company.Abstract_Interface_Lamda;

/**
 * 추상 클래스.
 *
 * 1. 일종의 미완성 클래스로, new 로 생성하는 것이 불가능하다!
 * 2. 다만 익명 클래스로 구현하면서 동시에 생성하는 것은 가능하다.
 * 3. 또는 상속을 받아서 일부를 구현해야만 사용이 가능하다.
 * 4. 추상 클래스를 가질 수 있다.
 *
 ******** 즉시 생성하려면 : 오직 익명 클래스로 넘겨 받아야 한다. 람다의 사용이 불가능하다.
 * */
public abstract class Abstract {
    /**
     * 자신의 인스턴스가 어떤 존재인지를 식별해주는 함수
     * */
    abstract String identity(String s);
}

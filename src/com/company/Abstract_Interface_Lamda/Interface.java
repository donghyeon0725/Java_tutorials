package com.company.Abstract_Interface_Lamda;

/**
 * 인터페이스
 *
 * 1. 일종의 가이드라인이다.
 * 2. 오직 함수만 가질 수 있다. (필드를 가질 수 없음)
 * 3. 다형성 구현을 위해서 (다양한 클래스가 공통적으로 메서드를 가지도록 하고 호환이 가능하도록 하는 것) 사용하기도 한다.
 * 4. 기본적으로는 인터페이스를 구현한 클래스로 구현하는 것이 일반적이다.
 * 5. 파라미터로 인터페이스 (클래스 처럼)를 넘겨 받는 메서드가 있는 경우가 있다. 아래의 3가지 경우 중 하나에 해당한다.
 *      - 인터페이스를 구현한 클래스를 넘겨 받고 싶은 경우   => 다형성 구현을 위함
 *      - 사용할 때, 즉시 구현 받고자 하는 경우            => 특정 조건이 받는 값에 따라 달라질 때 사용이 가능하다.
 *
 ******** 즉시 생성하려면 : 메서드가 1개인 경우 람다로 전달 받을 수 있다. new 와 익명 클래스를 통해 즉시 구현한 객체를 전달 받을 수도 있다. 또는 함수가 1개일 경우, 익명 함수를 통해서 전달 받을 수도 있는데, 이것을 람다라고 한다.
 * */
public interface Interface {
    /**
     * 자신의 인스턴스가 어떤 존재인지를 식별해주는 함수
     * */
    String identity(String str);
}

package com.company.Optional;

import java.util.*;

public class OptionalTest {
    static Account account = new Account("A");
    static Account account_admin = new Account("Admin");
    static Account account_null = null;

    /**
     * ofNullable => null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체
     * */
    static Optional<Account> maybeAccount = Optional.ofNullable(account);

    /**
     * of => null 인경우 NPE, 아니면 Optional 객체
     * */
    static Optional<Account> optAccount = Optional.of(account);
    //static Optional<Account> optAccount = Optional.of(account_null); null 이라서 에러

    /**
     * empty => null 을 담고 있는(비어있는) Optional 객체를 얻어옴
     * */
    static Optional<Account> emptyAccount = Optional.empty();

    /**
     * get : 값이 없으면 에러
     * */
    public static void get() {
        System.out.println(maybeAccount.get().getName()); // A
        // System.out.println(emptyAccount.get().getName()); 비어있는 객체에 대해서 에러
        System.out.println(optAccount.get().getName()); // A
    }

    /**
     * orElse 없으면 매개변수로 받은 인스턴스 return
     * */
    public static void orElse() {
        System.out.println(emptyAccount.orElse(account_admin).getName()); // Admin => 객체가 비어있으면 해당 객체를 던짐
    }

    /**
     * orElseGet 없는 경우 매개변수로 받은 함수 실행. 없는 경우에만 orElseGet를 호출하므로 orElse 보다 성능상의 이점이 있음
     * */
    public static void orElseGet() {
        System.out.println( //orElseGet 는 비어있는 경우에만 호출 되기 때문에 성능상의 이점을 가져갈 수 있음
            emptyAccount.orElseGet(()->
                    new Account("new Account")
            ).getName()); // new Account
    }

    /**
     * orElseThrow 없는 경우 매개변수로 받은 에러를 던짐짐
     * */
    public static void orElseThrow() {
        System.out.println( //orElseGet 는 비어있는 경우에만 호출 되기 때문에 성능상의 이점을 가져갈 수 있음
                emptyAccount.orElseThrow(() ->
                        new RuntimeException("error") // java.lang.RuntimeException: error
                )
        );
    }

    public static String getText() {
        return "Test";
    }

    /**
     * optional을 사용하는 방법 1
     * */
    public static void optionalTest1() {
        int length = Optional.ofNullable(getText()).map(String::length).orElse(0);
        System.out.println(length); // 4
    }

    /**
     * optional을 사용하는 방법 2
     * */
    public static void optionalTest2() {
        Address address = new Address.Builder().setCity("Guro").setStreet("길거리").setZipcode("10044").build();
        Member member = new Member.Builder().setId((long)29).setName("kim").setAddress(address).build();
        Order order = new Order.Builder().setDate(new Date()).setId((long)29).setMember(member).build();

        String city = getCityOfMemberFromOrder(order);

        System.out.println(city);
    }

    /**
     * optional을 사용하는 방법
     * 값중 하나라도 null 이 되면 Seoul을 반환한다.
     * */
    public static String getCityOfMemberFromOrder(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getMember) // 계속 Optional 객체를 반환한다.
                .map(Member::getAddress)
                .map(Address::getCity)
                .orElse("Seoul");
    }

    public void filterTest() {
        Address address = new Address.Builder().setCity("Guro").setStreet("길거리").setZipcode("10044").build();
        Member member = new Member.Builder().setId((long)29).setName("kim").setAddress(address).build();
        Order order = new Order.Builder().setDate(new Date()).setId((long)29).setMember(member).build();

        getMemberIfOrderWithin(order, 1000);
    }

    /**
     * filter 테스트
     * 시간 내에 주문한 멤버만 추려낸다.
     * */
    public Optional<Member> getMemberIfOrderWithin(Order order, int min) {
        return Optional.ofNullable(order) // ofNullable 을 통해 null 일수도 있다는 것을 추려내고 있다.
                .filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000) // 비지니스 로직만 따로 분리 되었다.
                .map(Order::getMember);
    }

    /**
     * 기존의 map Legacy 코드 교체 => null 처리를 어떻게 해줄 것인지
     * map 에서 가져오는 객체를 ofNullable 처리해줌. 그리고 null 처리를 함
     * */
    public static void changeLegacyCode() {
        Map<Integer, String> cities = new HashMap<>();
        cities.put(1, "seoul");
        cities.put(2, "guro");
        cities.put(3, "america");

        Optional<String> maybeCity = Optional.ofNullable(cities.get(4)); // Optional => null 일수도 있음을 알려주고 있다.
        String city1 = Optional.ofNullable(cities.get(4)).orElse("no city");
        String city2 = Optional.ofNullable(cities.get(1)).orElse("no city");
        int length = maybeCity.map(String::length).orElse(0); // null-safe
        System.out.println("length: " + length); // 0
        System.out.println("city1 : " + city1); //no city
        System.out.println("city2 : " + city2); // seoul
    }

    /**
     * 기존의 list Legacy 코드 교체 => null 처리를 어떻게 해줄 것인지
     * list 의 코드를 index에서 벗어났을 경우 빈 Optional 객체를 가져올 수 있도록 함.
     * */
    public static <T> Optional<T> getAsOptional(List<T> list, int index) {
        try {
            return Optional.of(list.get(index));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    /**
     * 리스트에서 객체를 Optional으로 받아와서 null 처리를 해주었음
     * */
    public static void LegacyListChangeToOptional() {
        List<String> cities = new ArrayList<>();
        cities.add("seoul");
        cities.add("goru");
        cities.add("america");

        Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
        int length = maybeCity.map(String::length).orElse(0); // null-safe
        System.out.println("length: " + length);
    }

    /**
     * ifPresent 테스트 => isPresent가 아님에 주의하자
     * 이것으로 특정 상황에서만 insert나 updatee 처리를 할 수 있게 되었다.
     * */
    public static void ifPresentTest() {
        List<String> cities = new ArrayList<>();
        cities.add("seoul");
        cities.add("goru");
        cities.add("america");

        Optional<String> maybeCity = getAsOptional(cities, 2); // Optional
        maybeCity.ifPresent(city -> {
            System.out.println("length: " + city.length());
        });
        // maybeCity 가 null이 아닐때만 작동한다.
    }

}

출처 : <https://www.daleseo.com/java8-optional-before/>

📌 NULL이란?
-
null 이라는 개념은 1965년에 Tony Hoare라는 영국의 컴퓨터 과학자에 의해 처음으로 고안 됨.  
당시 그는 "존재하지 않는 값"을 표현할 가장 편리한 방법이 null 참조였다고 생각했음.  
허나 그것을 큰 실수였다고 스스로 인정하였음  

<br/>

📌 NPE(NullPointerException)
-
자바 개발자들이 가장 골치 아프게 겪는 문제
가장 골치 아프게 겪는 null 포인터 예외 문제이다.  
컴파일 이전에는 조용하게 잠복해 있다가 런타임시에 문제가 되는 이 소스에 자바 개발자는 속수무책으로 당할 수 밖에 없었음

<br/>

```java
java.lang.NullPointerException
	at seo.dale.java.practice(OptionalTest.java:26)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
```

📌 null 처리에 취약한 코드
-
빌더 패턴을 이용하는 함수형 프로그래밍에서 null은 함수형 프로그래밍을 어렵게 하는 중요한 요인 중 하나.
아래 코드를 보면 

```java
/* 주문 */
public class Order {
	private Long id;
	private Date date;
	private Member member;
	// getters & setters
}

/* 회원 */
public class Member {
	private Long id;
	private String name;
	private Address address;
	// getters & setters
}

/* 주소 */
public class Address {
	private String street;
	private String city;
	private String zipcode;
	// getters & setters
}
```

<br/>

* **Order** 클래스는 **Member** 타입의 필드를 가지고, Member 클래스는 다시 **Address** 타입의 필드를 가집니다.  

그리고 어떤 주문을 한 회원이 어느 도시에 살고 있는지 알아내기 위해 다음과 같은 메소드가 있다고 가정.

```java
/* 주문을 한 회원이 살고 있는 도시를 반환한다 */
public String getCityOfMemberFromOrder(Order order) {
	return order.getMember().getAddress().getCity();
}
```
* 점(.)으로 많은 메소드들을 호출하고 있기 때문에 NPE 문제를 일으킬 수 있는 부분이 무려 3군데에나 있다.


📌 NPE 발생 시나리오
-
위 메소드에서 구체적으로 어떤 상황에서 NPE가 발생할 수 있는지 탐색과정을 짚어봅시다.
1. order 파라미터에서 null 값이 넘어오는 경우
2. order.getMember()의 결과가 null 인 경우
3. order.getMember.getAddress() 의 결과가 null 인 경우
4. order.getMember.getAddress.getCity() 의 결과가 null 인경우

4번은 NPE 발생의 경우는 아니지만, null을 return 해서 NPE 를 일으킬 가능성이 있는 부분이다.  
따라서 4번을 다음과 같이 처리해주지 않으면 마찬가지로 에러가능성이 다분하다.

<br/>


```java
String city = getCityOfMemberFromOrder(order); // returns null
System.out.println(city.length()); // throws NPE!
```

<br/>

전통적인(?) NPE 방어 패턴
-
1. 중첩하여 null 체크하기

    ```java
    public String getCityOfMemberFromOrder(Order order) {
        if (order != null) {
            Member member = order.getMember();
            if (member != null) {
                Address address = member.getAddress();
                if (address != null) {
                    String city = address.getCity();
                    if (city != null) {
                        return city;
                    }
                }
            }
        }
        return "Seoul"; // default
    }
    ```
* 들여쓰기 때문에 코드를 읽기가 매우 어려우며 핵심 비즈니스 파악이 쉽지 않습니다.

<br/>

  
2. 사방에서 return 하기

    ```java
    public String getCityOfMemberFromOrder(Order order) {
        if (order == null) {
            return "Seoul";
        }
        Member member = order.getMember();
        if (member == null) {
            return "Seoul";
        }
        Address address = member.getAddress();
        if (address == null) {
            return "Seoul";
        }
        String city = address.getCity();
        if (city == null) {
            return "Seoul";
        }
        return city;
    }
    ```
* 코드 읽기가 조금 쉬워지긴 했지만, 결과를 여러 곳에 리턴하기 때문에 유지 보수하기가 난해해졌습니다.
* 위 두 경우 도무 코드가 상당히 길어지고 지저분해짐


<br/>


📌 null의 저주
-
에초에 getCityOfMemberFromOrder() 메소드에 대한 우리의 요구 사항은 아래와 같이 명확하고 간단했음

    "어떤 주문을 한 회원이 어느 도시에 살고 있는지 알려주세요"

* 하지만 우리 코드는 if문과 return 문이 사방 팔방으로 도배가 되었습니다.
* 비즈니스 로직을 체크하려던 것인지 null 체크를 하려는 것인지 확인이 불가능하다.

<br/>


📌 정리
-
* 런타임에 NPE(NullPointerException)라는 예외를 발생시킬 수 있습니다.
* NPE 방어를 위해서 들어간 null 체크 로직 때문에 코드 가독성과 유지 보수성이 떨어집니다.

<br/>


📌 함수형 언어에서 그 해법을 찾다
-
스칼라나 히스켈과 같은 소위 함수형 언어들은 전혀 다른 방법으로 이 문제를 해결함.
자바가 "존재하지 않는 값"을 표현하기 위해 null을 사용했다면, 이 함수형 언어들을 "존재할지 안 할지 모르는 값"을 표현할 수 있는 별개의 타입을 가지고 있습니다.
그리고 이 값을 제어하기 위해 많은 API가 제공 되는데 자바 개발자는 이런 함수형 언어의 접근 방식에서 영감을 받아 **java.util.Optional<T>** 이라는 새로운 클래스를 도입함


<br/>

📌 Optional이란?
-
* 존재할 수도 있지만 안 할 수도 있는 객체
* 즉, null 이 될 수도 있는 객체 (래퍼클래스)
* 원소가 없거나 최대 하나 밖에 없는 Collection이나 Stream으로 생각해도 좋다.
* null 을 담아서 쉽게 제어할 수 있게 해주는 특수한 그릇으로 생각하면 됨

<br/>

📌 Optional의 효과
-
* Optional로 객체를 감싸서 사용하면
    - NPE를 유발할 수 있는 null을 직접 다루지 않아도 됨
    - 수고롭게 null 체크를 직접하지 않아도 됨
    - 명시적으로 해당 변수가 null 일 수도 있다는 가능성을 표현할 수 있기에 불필요한 방어 로직을 줄일 수 있음


<br/>


📌 Optional 기본 사용법
-

1. Optional 변수 선언하기
    - 제네릭을 제공하기에, 변수를 선언 할 때 명시한 타입 파라미터의 종류에 따라 객체의 타입이 결정 됨
   
    ```java
    Optional<Order> maybeOrder; // Order 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
    Optional<Member> optMember; // Member 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
    Optional<Address> address; // Address 타입의 객체를 감쌀 수 있는 Optional 타입의 변수
    ```
   변수명은 그냥 클래스 이름을 사용하기도 하나 "maybe"나 "opt"와 같은 접두어를 붙여서 Optional 타입의 변수라는 것을 좀 더 명확히 나타내기도 함
   
2. Optional 객체 생성하기
    - Optional 클래스는 간편하게 객체 생성을 할 수 있도록 3가지 정적 팩토리 메소드를 제공함
        1. Optional.empty() : null 을 담고 있는(비어있는) Optional 객체를 얻어옴. 이 빈 객체는 Optional 내부적으로 미리 생성해놓은 싱글턴 인스턴스
            ```java
            Optional<Member> maybeMember = Optional.empty();
            ```
           
        2. Optional.of(value) : null 이 아닌 객체를 담고 있는 객체 생성. null 이 넘어올 경우 NPE 를 던지기 때문에 **주의**
            ```java
            Optional<Member> maybeMember = Optional.of(aMember);
            ```
           
        3. Optional.ofNullable(value) : null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성합니다.
            ```java
            Optional<Member> maybeMember = Optional.ofNullable(aMember);
            Optional<Member> maybeNotMember = Optional.ofNullable(null);
            ```
            - Optional.empty(), Optional.ofNullable(value) 을 합쳐놓은 메소드라고 생각하면 됨. 
            - null이 넘어올 경우, NPE를 던지지 않고 Optional.empty()와 동일하게 비어 있는 Optional 객체를 얻어옵니다.
            - 해당 객체가 null인지 아닌지 자신이 없는 상황에서는 이 메소드를 사용하셔야 합니다.
            
3. Optional이 담고 있는 객체 접근하기
    - Optional 클래스는 담고 있는 객체를 꺼내오기 위해서 다양한 인스턴스 메소드를 제공합니다
    - 아래 메소드들은 모두 Optional이 담고 있는 객체가 존재할 경우 동일하게 해당 값을 반환합니다
    _ **다만** Optional이 비어있는 경우(즉, null을 담고 있는 경우), 다르게 작동합니다. 따라서 그 부분만 설명을 하자면
       
        ```java
        Account account = new Account("A");
        Account account_admin = new Account("Admin");
        Account account_null = null;
        ```
       위 두 코드를 작성했다고 가정
       
        ```java
        get()
      
        maybeAccount.get().getName()
        ```
        비어있는 Optional 객체에 대해서, NoSuchElementException을 던집니다.
        
        ```java
        orElse(T other)
      
        emptyAccount.orElse(account_admin).getName() => account_admin 반환 
        ```
        비어있는 Optional 객체에 대해서, 넘어온 인자를 반환합니다.
        
        ```java
        orElseGet(Supplier<? extends T> other)
      
        emptyAccount.orElseGet(()->
            new Account("new Account")
        ).getName()
        ```
        비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 객체를 반환합니다.
        orElse(T other)의 게으른 버전이라고 보시면 됩니다. 비어있는 경우에만 함수가 호출되기 때문에 orElse(T other) 대비 성능상 이점을 기대할 수 있습니다.
        
        ```java
        orElseThrow(Supplier<? extends X> exceptionSupplier)
      
        emptyAccount.orElseThrow(() ->
            new RuntimeException("error")
        )
        ```
        비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 예외를 던집니다.
        
Optional의 잘못된 사용
-
위에서 설명드린 것 처럼 get() 메소드는 비어있는 Optional 객체를 대상으로 호출할 경우, 예외를 발생시키므로 다음과 같이 객재 존재 여부를 bool 타입으로 반환하는 isPresent()라는 메소드를 통해 null 체크가 필요합니다.

```java
String text = getText();
Optional<String> maybeText = Optional.ofNullable(text);
int length;
if (maybeText.isPresent()) {
	length = maybeText.get().length();
} else {
	length = 0;
}
```

같은 코드를 다시 Optional 없이 작성해보겠습니다.

```java
String text = getText();
int length;
if (text != null) {
	length = maybeText.get().length();
} else {
	length = 0;
}
```

Optional을 사용하려는 이유는 앞에서 설명드렸던 것 처럼 고통스러운 null 처리를 직접하지 않고 **Optional 클래스에 위임**하기 위함 
따라서 null 체크를 하지 않는 것이 좋다.

다른 잘못된 예제로 이 전 포스트에서 보았던 getCityOfMemberFromOrder() 메소드를 같은 스타일로 작성하면 다음과 같습니다. 이 전 포스트에서 보았던 코드와 별반 다르지 않은 수준의, 사실 오히려 살짝 더 복잡해 보이는 끔찍한 코드가 탄생하였습니다. 무엇이 어디서부터 어떻게 잘못된 걸까요?

```java
/* 주문을 한 회원이 살고 있는 도시를 반환한다 */
public String getCityOfMemberFromOrder(Order order) {
	Optional<Order> maybeOrder = Optional.ofNullable(order);
	if (maybeOrder.isPresent()) {
		Optional<Member> maybeMember = Optional.ofNullable(maybeOrder.get());
		if (maybeMember.isPresent()) {
			Optional<Address> maybeAddress = Optional.ofNullable(maybeMember.get());
			if (maybeAddress.isPresent()) {
				Address address = maybeAddress.get();
				Optinal<String> maybeCity = Optional.ofNullable(address.getCity());
				if (maybeCity.isPresent()) {
					return maybeCity.get();
				}
			}
		}
	}
	return "Seoul";
}
```

ptional을 정확히 이해하고 제대로 사용하실 수 있는 개발자라면 첫번째 예제의 코드는 다음과 같이 한 줄의 코드로 작성할 수 있어야 합니다. 다시 말해서, 기존에 조건문으로 null을 대하던 생각을 함수형 사고로 완전히 새롭게 바꿔야 합니다.

```java
int length = Optional.ofNullable(getText()).map(String::length).orElse(0);
```

* map 처럼 원하는 형태로 반환 한 이후, orElse으로 null처리를 해주면 된다.

📌 Stream처럼 사용하기
-
Optional을 제대로 사용하려면, Optional을 최대 1개의 원소를 가지고 있는 특별한 Stream이라고 생각해야함  
Optional 클래스와 Stream 클래스 간에 직접적인 구현이나 상속관계는 없지만 사용 방법이나 기본 사상이 매우 유사하기 때문입니다.  
Stream 클래스가 가지고 있는 map()이나 flatMap(), filter()와 같은 메소드를 Optional도 가지고 있습니다. 따라서 Stream을 능숙하게 다루시는 분이시라면 Optional도 어렵지 않게 다루실 수 있음


<br/>

📌 map()으로 변신하기
-
Stream API를 나루듯이 Optional API를 사용하여 첫 번째 포스트의 getCityOfMemberFromOrder() 메소드를 리펙토링 해보겠습니다.

```java
/* 주문을 한 회원이 살고 있는 도시를 반환한다 */
public String getCityOfMemberFromOrder(Order order) {
	return Optional.ofNullable(order)
			.map(Order::getMember)
			.map(Member::getAddress)
			.map(Address::getCity)
			.orElse("Seoul");
}
```
* [optionalTest](./OptionalTest.java) => optionalTest 실행
* 주의할 점은 orElse 한번만 있으면 된다는 사실이다!
* 첫 번째 포스트에서 다루었던 2가지 전통적인 NPE 방어 패턴에 비해 훨씬 간결하고 명확해진 코드를 볼 수 있습니다. 우선 기존에 존재하던 조건문들이 모두 사라지고 Optional의 수려한(fluent) API에 의해서 단순한 메소드 체이닝으로 모두 대체되었습니다. 메소드 체이닝의 간 단계 별로 좀 더 상세히 짚어보겠습니다.
* ofNullable() 정적 팩토리 메소드를 호출하여 Order 객체를 Optional로 감싸주었습니다.
* 혹시 Order 객체가 null인 경우를 대비하여 of() 대신에 ofNullable()을 사용했습니다.
* 3번의 map() 메소드의 연쇄 호출을 통해서 Optional 객체를 3번 변환하였습니다. 매 번 다른 메소드 레퍼런스를 인자로 넘겨서 Optional에 담긴 객체의 타입을 바꿔주었습니다. (Optional<Order> -> Optional<Member> -> Optional<Address> -> Optional<String>)
* 마무리 작업으로 orElse() 메소드를 호출하여 이 전 과정을 통해 얻은 Optional이 비어있을 경우, 디폴트로 사용할 도시 이름을 세팅해주고 있습니다.
* 어떠신가요? Optional를 제대로 활용하여 처음으로 null-safe한 코드를 작성해보았습니다. 이 전에 Stream을 사용하여 이런 식으로 코딩을 해보신 적이 없으시다면 많이 낯설 수도 있습니다. Java8의 람다식과 메소드 레퍼런스를 좋아하신다면 이 코드가 마음에 드실 겁니다. :smile:


<br/>


📌 filter()로 레벨업
-

* Java8 이 전에 NPE 방지를 위해서 다음과 같이 null 체크로 시작하는 if 조건문 패턴을 자주 보셨을 겁니다.

```java
if (obj != null && obj.do() ...)
```

* 예를 들어, 주어진 시간(분) 내에 생성된 주문을 한 경우에만 해당 회원 정보를 구하는 메소드를 위 패턴으로 작성해보았습니다.


```java
public Member getMemberIfOrderWithin(Order order, int min) {
	if (order != null && order.getDate().getTime() > System.currentTimeMillis() - min * 1000) {
		return order.getMember();
	}
}

```

* 위 코드는 if 조건문 내에 null 체크와 비지니스 로직이 혼재되어 있어서 가독성이 떨어집니다. 게다가 null을 리턴할 수 있기 때문에 메소드 호출부에 NPE 위험을 전파하고 있습니다.

* 반면에 filter() 메소드를 사용하면 if 조건문 없이 메소드 연쇄 호출만으로도 좀 더 읽기 편한 코드를 작성할 수 있습니다. 뿐만 아니라, 메소드의 리턴 타입을 Optional로 사용함으로써 호출자에게 해당 메소드가 null을 담고 있는 Optional을 반환할 수도 있다는 것을 명시적으로 알려주고 있습니다.

```java
public Optional<Member> getMemberIfOrderWithin(Order order, int min) {
	return Optional.ofNullable(order)
			.filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000)
			.map(Order::getMember);
}
```

* filter() 메소드는 넘어온 함수형 인자의 리턴 값이 false인 경우, Optional을 비워버리므로 그 이후 메소드 호출은 의미가 없어지게 됩니다. 
* Stream 클래스의 filter() 메소드와 동작 방식이 동일하지만, Optional의 경우 원소가 하나 밖에 없기 때문에 이런 효과가 나타나게 됩니다.


<br/>

📌 Java8 이 전에 개발된 코드를 Optional하게 바꾸기
-
* Java8 이 전에 개발된 API들은 안타깝게도 당시에 Optional 클래스가 없었기 때문에 null-safe하지 않습니다. 
* 심지어 Java 표준 API 조차 하위 호환성을 보장을 위해서 기존 API에 Optional 클래스를 적용할 수 없었습니다. 다행이도 우리는 스스로 Optional 클래스를 사용하여 기존 코드가 null-safe하도록 바꿔줄 수 있습니다.


<br/>

📌 메소드의 반환값이 존재하지 않을 때 전통적인 처리 패턴
-
* 이전 개발된 메소드들은 반환할 값이 존재하지 않을 경우, 크게 2가지 패턴으로 처리하였습니다. 각 처리 패턴을 어떻게 개선할 수 있는지 예제 코드를 통해 살펴보겠습니다.


1. null 반환
    * Map 인터페이스의 get() 메소드는 주어진 인덱스에 해당하는 값이 없으면 null을 반환합니다.

    ```java
    Map<Integer, String> cities = new HashMap<>();
    cities.put(1, "Seoul");
    cities.put(2, "Busan");
    cities.put(3, "Daejeon");
    ```

    * 따라서 해당 API를 사용하는 코드를 null-safe하게 만들기 위해서 null 체크를 해줘야 합니다.
    
    ```java
    String city = cities.get(4); // returns null
    int length = city == null ? 0 : city.length(); // null check
    System.out.println(length);
    ```

    * 다음과 같이 get() 메소드의 반환 값을 Optional로 감싸주면, 자연스럽게 null-safe한 코드가 됩니다.

    ```java
    Optional<String> maybeCity = Optional.ofNullable(cities.get(4)); // Optional
    int length = maybeCity.map(String::length).orElse(0); // null-safe
    System.out.println("length: " + length);
    
    ```
    * 읽기 쉬운 코드를 작성하기 위해 map()과 orElse() 메소드가 어떻게 사용되고 있는지 주의깊게 보시기 바랍니다. 도시 문자열을 길이로 변환하고 디폴트 값을 설정해주는 과정을 한눈에 파악하기 편하지 않으신가요?



2. 예외 발생
    * 두번째 패턴은 null을 반환하지 않고 예외를 던저버리는 경우입니다. List 인터페이스의 get() 메소드는 주어진 인덱스에 해당하는 값이 없으면 ArrayIndexOutOfBoundsException을 던집니다.

    ```java
    List<String> cities = Arrays.asList("Seoul", "Busan", "Daejeon");
    ```

    * 따라서, 다음과 같이 try-catch 구문을 사용하여 예외 처리를 해줘야 하며, 예외 처리 후에도 null check도 해줘야 하기 때문에 코드가 지저분해집니다.

    ```java
    String city = null;
    try {
        city = cities.get(3); // throws exception
    } catch (ArrayIndexOutOfBoundsException e) {
        // ignore
    }
    int length = city == null ? 0 : city.length(); // null check
    System.out.println(length);
    ```

    * 이런 경우, 다음과 같이 예외 처리부를 감싸서 정적 유틸리티 메소드로 분리해주면 좋습니다. Optional 클래스의 정적 팩토리 메소드를 사용하여 정상 처리 시와 예외 처리 시에 반환할 Optional 객체를 각각 지정해주었습니다. 이 경우에는 Optional에 담을 객체가 null인지 아닌지 확실히 알 수 있기 때문에 Optional.ofNullable() 대신에 다른 2개의 정적 팩토리 메소드를 쓸 수 있다는 점을 주의깊게 보시기 바랍니다.
    
    ```java
    public static <T> Optional<T> getAsOptional(List<T> list, int index) {
        try {
            return Optional.of(list.get(index));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
    ```
    * 아래와 같이 정적 유틸리티 메소드를 통해 Optional 객체를 확보 후에 null-safe하게 코딩할 수 있습니다.
    
    ```java
    Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
    int length = maybeCity.map(String::length).orElse(0); // null-safe
    System.out.println("length: " + length);
    ```


ifPresent() 메소드
-
* 아, 이 전 포스트에서 미처 소개드리지 않은 조금 특별한 메소드가 하나 있습니다. 
* 바로 ifPresent() 메소드인데요. 많은 분들이 isPresent()와 혼동하기도 하는 녀석입니다.

* ifPresent(Consumer<? super T> consumer): 이 메소드는 특정 결과를 반환하는 대신에 Optional 객체가 감싸고 있는 값이 존재할 경우에만 실행될 로직을 함수형 인자로 넘길 수 있습니다.
* 함수형 인자로 람다식이나 메소드 레퍼런스가 넘어올 수 있는데요. 마치 비동기 메소드의 콜백 함수처럼 작동합니다. 바로 전 예제의 코드를 ifPresent() 메소드를 이용해서 재작성하면 다음과 같습니다

```java
Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
maybeCity.ifPresent(city -> {
	System.out.println("length: " + city.length());
});
```

















        
    
    
출처 : <https://www.daleseo.com/java8-optional-before/>

NULL이란?
-
null 이라는 개념은 1965년에 Tony Hoare라는 영국의 컴퓨터 과학자에 의해 처음으로 고안 됨.  
당시 그는 "존재하지 않는 값"을 표현할 가장 편리한 방법이 null 참조였다고 생각했음.  
허나 그것을 큰 실수였다고 스스로 인정하였음  

NPE(NullPointerException)
-
자바 개발자들이 가장 골치 아프게 겪는 문제
가장 골치 아프게 겪는 null 포인터 예외 문제이다.  
컴파일 이전에는 조용하게 잠복해 있다가 런타임시에 문제가 되는 이 소스에 자바 개발자는 속수무책으로 당할 수 밖에 없었습니다.

```java
java.lang.NullPointerException
	at seo.dale.java.practice(OptionalTest.java:26)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
```

null 처리에 취약한 코드
-
빌더 패턴을 이용하는 함수형 프로그래밍에서 null은 함수형 프로그래밍을 어렵게 하는 중요한 요인 중 하나입니다.
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
* **Order** 클래스는 **Member** 타입의 필드를 가지고, Member 클래스는 다시 **Address** 타입의 필드를 가집니다.  

그리고 어떤 주문을 한 회원이 어느 도시에 살고 있는지 알아내기 위해 다음과 같은 메소드가 있다고 가정을 해봅니다.
```java
/* 주문을 한 회원이 살고 있는 도시를 반환한다 */
public String getCityOfMemberFromOrder(Order order) {
	return order.getMember().getAddress().getCity();
}
```
* 점(.)으로 많은 메소드들을 호출하고 있기 때문에 NPE 문제를 일으킬 수 있는 부분이 무려 3군데에나 있다.


NPE 발생 시나리오
-
위 메소드에서 구체적으로 어떤 상황에서 NPE가 발생할 수 있는지 탐색과정을 짚어봅시다.
1. order 파라미터에서 null 값이 넘어오는 경우
2. order.getMember()의 결과가 null 인 경우
3. order.getMember.getAddress() 의 결과가 null 인 경우
4. order.getMember.getAddress.getCity() 의 결과가 null 인경우

4번은 NPE 발생의 경우는 아니지만, null을 return 해서 NPE 를 일으킬 가능성이 있는 부분이다.  
따라서 4번을 다음과 같이 처리해주지 않으면 마찬가지로 에러가능성이 다분하다.

```java
String city = getCityOfMemberFromOrder(order); // returns null
System.out.println(city.length()); // throws NPE!
```

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


null의 저주
-
에초에 getCityOfMemberFromOrder() 메소드에 대한 우리의 요구 사항은 아래와 같이 명확하고 간단했음

    "어떤 주문을 한 회원이 어느 도시에 살고 있는지 알려주세요"

* 하지만 우리 코드는 if문과 return 문이 사방 팔방으로 도배가 되었습니다.
* 비즈니스 로직을 체크하려던 것인지 null 체크를 하려는 것인지 확인이 불가능하다.


정리
-
* 런타임에 NPE(NullPointerException)라는 예외를 발생시킬 수 있습니다.
* NPE 방어를 위해서 들어간 null 체크 로직 때문에 코드 가독성과 유지 보수성이 떨어집니다.


함수형 언어에서 그 해법을 찾다
-
스칼라나 히스켈과 같은 소위 함수형 언어들은 전혀 다른 방법으로 이 문제를 해결함.
자바가 "존재하지 않는 값"을 표현하기 위해 null을 사용했다면, 이 함수형 언어들을 "존재할지 안 할지 모르는 값"을 표현할 수 있는 별개의 타입을 가지고 있습니다.
그리고 이 값을 제어하기 위해 많은 API가 제공 되는데 자바 개발자는 이런 함수형 언어의 접근 방식에서 영감을 받아 **java.util.Optional<T>** 이라는 새로운 클래스를 도입함


Optional이란?
-
* 존재할 수도 있지만 안 할 수도 있는 객체
* 즉, null 이 될 수도 있는 객체 (래퍼클래스)
* 원소가 없거나 최대 하나 밖에 없는 Collection이나 Stream으로 생각해도 좋다.
* null 을 담아서 쉽게 제어할 수 있게 해주는 특수한 그릇으로 생각하면 됨


Optional의 효과
-
* Optional로 객체를 감싸서 사용하면
    - NPE를 유발할 수 있는 null을 직접 다루지 않아도 됨
    - 수고롭게 null 체크를 직접하지 않아도 됨
    - 명시적으로 해당 변수가 null 일 수도 있다는 가능성을 표현할 수 있기에 불필요한 방어 로직을 줄일 수 있음
    

Optional 기본 사용법
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
    - Optional 클래스는 간편하게 객체 생성을 할 수 있도록 3가지 정적 팩토리 메소드를 제공합니다.
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
        get()
        ```
        비어있는 Optional 객체에 대해서, NoSuchElementException을 던집니다.
        
        ```java
        orElse(T other)
        ```
        비어있는 Optional 객체에 대해서, 넘어온 인자를 반환합니다.
        
        ```java
        orElseGet(Supplier<? extends T> other)
        ```
        비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 객체를 반환합니다.
        orElse(T other)의 게으른 버전이라고 보시면 됩니다. 비어있는 경우에만 함수가 호출되기 때문에 orElse(T other) 대비 성능상 이점을 기대할 수 있습니다.
        
        ```java
        orElseThrow(Supplier<? extends X> exceptionSupplier)
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

Stream처럼 사용하기
-
Optional을 제대로 사용하려면, Optional을 최대 1개의 원소를 가지고 있는 특별한 Stream이라고 생각해야함  
Optional 클래스와 Stream 클래스 간에 직접적인 구현이나 상속관계는 없지만 사용 방법이나 기본 사상이 매우 유사하기 때문입니다.  
Stream 클래스가 가지고 있는 map()이나 flatMap(), filter()와 같은 메소드를 Optional도 가지고 있습니다. 따라서 Stream을 능숙하게 다루시는 분이시라면 Optional도 어렵지 않게 다루실 수 있으실 겁니다.



        
    
    
함수형 인터페이스
-

<br/>

📌 Imperative approach VS Declarative approach
-

```java
// 리스트가 있다고 가정
List<Person> people = Arrays.asList(
    new Person("John", Gender.MALE),
    new Person("Maria", Gender.FEMALE),
    new Person("Aisha", Gender.MALE),
    new Person("Alex", Gender.FEMALE),
    new Person("Alice", Gender.MALE)
);
```

* Imperative approach
    * 명령적 접근 => 인덱스를 스스로 제어. 또는 시스템 적인 작업을 개발자가 직접해줌
    ```java
    for (Person person : people) {
        if (Gender.FEMALE.equals(person.gender)) {
            females.add(person);
        }
    }
    ```
  
* Declarative approach
    * 선언적 접근 => 개발자는 오직 비즈니스적 로직에만 관심을 가질 수 있게 해줌. 언어단에서 리스트를 반복해줌
    ```java
    people.stream()
        .filter(person -> Gender.MALE.equals(person.gender))
        .collect(Collectors.toList())
        .forEach(System.out::println);
    ```

    
📌 함수형 인터페이스 종류
-
* 인터페이스는 람다를 받거나 함수를 참조할 때 쓸수 있음

|인터페이스|설명|특징|
|:---:|:---:|:---:|
|Consumer|매개값은 있고, 리턴값은 없음|**소비**|
|Supplier|매개값은 없고, 리턴값은 있음|**공급**|
|Function|매개값도 있고, 리턴값도 있음[주로 매개값을 리턴값으로 매핑(타입 변환)]|**함수**|
|Operator|매개값도 있고, 리턴값도 있음[주로 매개값을 연산하고 결과를 리턴]|**운영**|
|Predicate|매개값은 있고, 리턴 타입은 boolean[매개값을 조사해서 true/false를 리턴]|**서술**|


|인터페이스|설명|
|:---:|:---:|
|Consumer<T>|매개변수 하나 받음|
|BiConsumer<T, U>|매개변수를 하나 더 받고 싶을 때|
|DoubleConsumer<T>|받는 매개변수를 double로 고정|
|IntConsumer<T>|'' int 로 고정|
|LongConsumer<T>|'' long 으로 고정|
|ObjDoubleConsumer<T, double>|매개변수 T와 double을 하나 받음|
|ObjIntConsumer<T, int>|매개변수 T와 int을 하나 받음|
|ObjLongConsumer<T, long>|매개변수 T와 long을 하나 받음|

* 참고하기 : <https://moreget.github.io/dev-00000061-Java-Consumer_Supplier/>

<br/>

---

📌 Function 인터페이스
-
* 매개변수를 받고 리턴값이 있는 람다 또는 함수를 받을 수 있는 인터페이스
* 사용 
    - 선언 :
        ```java
        Function<Integer, Integer> incrementByOneFunction = number -> number + 1;
        ```
    - 선언 (매개변수를 하나 더 받는 경우) :
        ```java
        int incrementByOneAndMultiply(int number, int numToMultiplyBy) { return (number+1) * numToMultiplyBy; }
        ```
    - 호출 : 
        ```java
        incrementByOneFunction.apply(1);
        ```
    - 인터페이스 중첩 : 
        ```java
        incrementByOneFunction.andThen(multipleBy10Function); // => 이 때 multipleBy10Function 는 같은 매개변수와 리턴타입을 가진 Function 인터페이스
        ```
* 실행 : [_Function.java](./_Function.java)


<br/>

📌 Predicate 인터페이스 
-
* 매개값은 있고, 리턴 타입은 boolean 타입인 함수를 참조 또는 람다를 받을 수 있는 인터페이스
* 사용 
    - 선언 : 
        ```java
        Predicate<String> isPhoneNumberValidPredicate = phoneNumber -> phoneNumber.startsWith("07") && phoneNumber.length() == 11;
        ```
    - 호출 : 
        ```java
        isPhoneNumberValidPredicate.test("07000000000");
        ```
    - 중첩 :
        ```java
        isPhoneNumberValidPredicate.and(containsNumber3); // containsNumber3는 같은 타입의 인터페이스
        ```
* 실행 : [_Predicate.java](./_Predicate.java)    


<br/>

📌 Consumer 인터페이스
-
* 매개변수는 있고 리턴 타입은 없음
    - 선언 :
        ```java
        Consumer<Customer> greetCustomerConsumer = customer -> System.out.println(customer.name);
        ```
    - 호출 : 
        ```java
        greetCustomerConsumer.accept(maria);
        ```
    - 매개변수를 2개 받는 경우 : 
        ```java
        BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = ((customer, showPhoneNumber) -> System.out.println(customer.name + " : " + (showPhoneNumber ? customer.customerPhoneNumber : "******") );
        ```


<br/>

📌 Supplier 인터페이스
-
* 매개변수는 없고 리턴타입만 있는 함수를 참조 또는 람다를 받음
    - 선언 ;
        ```java
        Supplier<String> getDBConnectionUrlSupplier = () -> "jdbc://localhost:5432/user";
        ```
    - 호출 : 
        ```java
        getDBConnectionUrlSupplier.get();
        ```
    - 응용 : 
        ```java
        Supplier<List<String>> getDBConnectionUrlsSupplier = () -> Arrays.asList(
          "jdbc://localhost:5432/user"
          ,"jdbc://localhost:5432/customer"
        ); // => 필요한 값의 리스트를 상수처럼 사용
        ```
    - 용도 : 주로 상수처럼 사용하는 것 같고 가끔 랜덤 값을 리턴하는 함수 등등으로 사용해도 될 것 같음

        
        
        
    
    
 
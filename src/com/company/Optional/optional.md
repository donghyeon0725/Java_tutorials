μΆμ² : <https://www.daleseo.com/java8-optional-before/>

π NULLμ΄λ?
-
null μ΄λΌλ κ°λμ 1965λμ Tony HoareλΌλ μκ΅­μ μ»΄ν¨ν° κ³Όνμμ μν΄ μ²μμΌλ‘ κ³ μ λ¨.  
λΉμ κ·Έλ "μ‘΄μ¬νμ§ μλ κ°"μ ννν  κ°μ₯ νΈλ¦¬ν λ°©λ²μ΄ null μ°Έμ‘°μλ€κ³  μκ°νμ.  
νλ κ·Έκ²μ ν° μ€μμλ€κ³  μ€μ€λ‘ μΈμ νμμ  

<br/>

π NPE(NullPointerException)
-
μλ° κ°λ°μλ€μ΄ κ°μ₯ κ³¨μΉ μνκ² κ²ͺλ λ¬Έμ 
κ°μ₯ κ³¨μΉ μνκ² κ²ͺλ null ν¬μΈν° μμΈ λ¬Έμ μ΄λ€.  
μ»΄νμΌ μ΄μ μλ μ‘°μ©νκ² μ λ³΅ν΄ μλ€κ° λ°νμμμ λ¬Έμ κ° λλ μ΄ μμ€μ μλ° κ°λ°μλ μμλ¬΄μ±μΌλ‘ λΉν  μ λ°μ μμμ

<br/>

```java
java.lang.NullPointerException
	at seo.dale.java.practice(OptionalTest.java:26)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
```

π null μ²λ¦¬μ μ·¨μ½ν μ½λ
-
λΉλ ν¨ν΄μ μ΄μ©νλ ν¨μν νλ‘κ·Έλλ°μμ nullμ ν¨μν νλ‘κ·Έλλ°μ μ΄λ ΅κ² νλ μ€μν μμΈ μ€ νλ.
μλ μ½λλ₯Ό λ³΄λ©΄ 

```java
/* μ£Όλ¬Έ */
public class Order {
	private Long id;
	private Date date;
	private Member member;
	// getters & setters
}

/* νμ */
public class Member {
	private Long id;
	private String name;
	private Address address;
	// getters & setters
}

/* μ£Όμ */
public class Address {
	private String street;
	private String city;
	private String zipcode;
	// getters & setters
}
```

<br/>

* **Order** ν΄λμ€λ **Member** νμμ νλλ₯Ό κ°μ§κ³ , Member ν΄λμ€λ λ€μ **Address** νμμ νλλ₯Ό κ°μ§λλ€.  

κ·Έλ¦¬κ³  μ΄λ€ μ£Όλ¬Έμ ν νμμ΄ μ΄λ λμμ μ΄κ³  μλμ§ μμλ΄κΈ° μν΄ λ€μκ³Ό κ°μ λ©μλκ° μλ€κ³  κ°μ .

```java
/* μ£Όλ¬Έμ ν νμμ΄ μ΄κ³  μλ λμλ₯Ό λ°ννλ€ */
public String getCityOfMemberFromOrder(Order order) {
	return order.getMember().getAddress().getCity();
}
```
* μ (.)μΌλ‘ λ§μ λ©μλλ€μ νΈμΆνκ³  μκΈ° λλ¬Έμ NPE λ¬Έμ λ₯Ό μΌμΌν¬ μ μλ λΆλΆμ΄ λ¬΄λ € 3κ΅°λ°μλ μλ€.


π NPE λ°μ μλλ¦¬μ€
-
μ λ©μλμμ κ΅¬μ²΄μ μΌλ‘ μ΄λ€ μν©μμ NPEκ° λ°μν  μ μλμ§ νμκ³Όμ μ μ§μ΄λ΄μλ€.
1. order νλΌλ―Έν°μμ null κ°μ΄ λμ΄μ€λ κ²½μ°
2. order.getMember()μ κ²°κ³Όκ° null μΈ κ²½μ°
3. order.getMember.getAddress() μ κ²°κ³Όκ° null μΈ κ²½μ°
4. order.getMember.getAddress.getCity() μ κ²°κ³Όκ° null μΈκ²½μ°

4λ²μ NPE λ°μμ κ²½μ°λ μλμ§λ§, nullμ return ν΄μ NPE λ₯Ό μΌμΌν¬ κ°λ₯μ±μ΄ μλ λΆλΆμ΄λ€.  
λ°λΌμ 4λ²μ λ€μκ³Ό κ°μ΄ μ²λ¦¬ν΄μ£Όμ§ μμΌλ©΄ λ§μ°¬κ°μ§λ‘ μλ¬κ°λ₯μ±μ΄ λ€λΆνλ€.

<br/>


```java
String city = getCityOfMemberFromOrder(order); // returns null
System.out.println(city.length()); // throws NPE!
```

<br/>

μ ν΅μ μΈ(?) NPE λ°©μ΄ ν¨ν΄
-
1. μ€μ²©νμ¬ null μ²΄ν¬νκΈ°

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
* λ€μ¬μ°κΈ° λλ¬Έμ μ½λλ₯Ό μ½κΈ°κ° λ§€μ° μ΄λ €μ°λ©° ν΅μ¬ λΉμ¦λμ€ νμμ΄ μ½μ§ μμ΅λλ€.

<br/>

  
2. μ¬λ°©μμ return νκΈ°

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
* μ½λ μ½κΈ°κ° μ‘°κΈ μ¬μμ§κΈ΄ νμ§λ§, κ²°κ³Όλ₯Ό μ¬λ¬ κ³³μ λ¦¬ν΄νκΈ° λλ¬Έμ μ μ§ λ³΄μνκΈ°κ° λν΄ν΄μ‘μ΅λλ€.
* μ λ κ²½μ° λλ¬΄ μ½λκ° μλΉν κΈΈμ΄μ§κ³  μ§μ λΆν΄μ§


<br/>


π nullμ μ μ£Ό
-
μμ΄μ getCityOfMemberFromOrder() λ©μλμ λν μ°λ¦¬μ μκ΅¬ μ¬ν­μ μλμ κ°μ΄ λͺννκ³  κ°λ¨νμ

    "μ΄λ€ μ£Όλ¬Έμ ν νμμ΄ μ΄λ λμμ μ΄κ³  μλμ§ μλ €μ£ΌμΈμ"

* νμ§λ§ μ°λ¦¬ μ½λλ ifλ¬Έκ³Ό return λ¬Έμ΄ μ¬λ°© νλ°©μΌλ‘ λλ°°κ° λμμ΅λλ€.
* λΉμ¦λμ€ λ‘μ§μ μ²΄ν¬νλ €λ κ²μΈμ§ null μ²΄ν¬λ₯Ό νλ €λ κ²μΈμ§ νμΈμ΄ λΆκ°λ₯νλ€.

<br/>


π μ λ¦¬
-
* λ°νμμ NPE(NullPointerException)λΌλ μμΈλ₯Ό λ°μμν¬ μ μμ΅λλ€.
* NPE λ°©μ΄λ₯Ό μν΄μ λ€μ΄κ° null μ²΄ν¬ λ‘μ§ λλ¬Έμ μ½λ κ°λμ±κ³Ό μ μ§ λ³΄μμ±μ΄ λ¨μ΄μ§λλ€.

<br/>


π ν¨μν μΈμ΄μμ κ·Έ ν΄λ²μ μ°Ύλ€
-
μ€μΉΌλΌλ νμ€μΌκ³Ό κ°μ μμ ν¨μν μΈμ΄λ€μ μ ν λ€λ₯Έ λ°©λ²μΌλ‘ μ΄ λ¬Έμ λ₯Ό ν΄κ²°ν¨.
μλ°κ° "μ‘΄μ¬νμ§ μλ κ°"μ νννκΈ° μν΄ nullμ μ¬μ©νλ€λ©΄, μ΄ ν¨μν μΈμ΄λ€μ "μ‘΄μ¬ν μ§ μ ν μ§ λͺ¨λ₯΄λ κ°"μ ννν  μ μλ λ³κ°μ νμμ κ°μ§κ³  μμ΅λλ€.
κ·Έλ¦¬κ³  μ΄ κ°μ μ μ΄νκΈ° μν΄ λ§μ APIκ° μ κ³΅ λλλ° μλ° κ°λ°μλ μ΄λ° ν¨μν μΈμ΄μ μ κ·Ό λ°©μμμ μκ°μ λ°μ **java.util.Optional<T>** μ΄λΌλ μλ‘μ΄ ν΄λμ€λ₯Ό λμν¨


<br/>

π Optionalμ΄λ?
-
* μ‘΄μ¬ν  μλ μμ§λ§ μ ν  μλ μλ κ°μ²΄
* μ¦, null μ΄ λ  μλ μλ κ°μ²΄ (λνΌν΄λμ€)
* μμκ° μκ±°λ μ΅λ νλ λ°μ μλ Collectionμ΄λ StreamμΌλ‘ μκ°ν΄λ μ’λ€.
* null μ λ΄μμ μ½κ² μ μ΄ν  μ μκ² ν΄μ£Όλ νΉμν κ·Έλ¦μΌλ‘ μκ°νλ©΄ λ¨

<br/>

π Optionalμ ν¨κ³Ό
-
* Optionalλ‘ κ°μ²΄λ₯Ό κ°μΈμ μ¬μ©νλ©΄
    - NPEλ₯Ό μ λ°ν  μ μλ nullμ μ§μ  λ€λ£¨μ§ μμλ λ¨
    - μκ³ λ‘­κ² null μ²΄ν¬λ₯Ό μ§μ νμ§ μμλ λ¨
    - λͺμμ μΌλ‘ ν΄λΉ λ³μκ° null μΌ μλ μλ€λ κ°λ₯μ±μ ννν  μ μκΈ°μ λΆνμν λ°©μ΄ λ‘μ§μ μ€μΌ μ μμ


<br/>


π Optional κΈ°λ³Έ μ¬μ©λ²
-

1. Optional λ³μ μ μΈνκΈ°
    - μ λ€λ¦­μ μ κ³΅νκΈ°μ, λ³μλ₯Ό μ μΈ ν  λ λͺμν νμ νλΌλ―Έν°μ μ’λ₯μ λ°λΌ κ°μ²΄μ νμμ΄ κ²°μ  λ¨
   
    ```java
    Optional<Order> maybeOrder; // Order νμμ κ°μ²΄λ₯Ό κ°μ μ μλ Optional νμμ λ³μ
    Optional<Member> optMember; // Member νμμ κ°μ²΄λ₯Ό κ°μ μ μλ Optional νμμ λ³μ
    Optional<Address> address; // Address νμμ κ°μ²΄λ₯Ό κ°μ μ μλ Optional νμμ λ³μ
    ```
   λ³μλͺμ κ·Έλ₯ ν΄λμ€ μ΄λ¦μ μ¬μ©νκΈ°λ νλ "maybe"λ "opt"μ κ°μ μ λμ΄λ₯Ό λΆμ¬μ Optional νμμ λ³μλΌλ κ²μ μ’ λ λͺνν λνλ΄κΈ°λ ν¨
   
2. Optional κ°μ²΄ μμ±νκΈ°
    - Optional ν΄λμ€λ κ°νΈνκ² κ°μ²΄ μμ±μ ν  μ μλλ‘ 3κ°μ§ μ μ  ν©ν λ¦¬ λ©μλλ₯Ό μ κ³΅ν¨
        1. Optional.empty() : null μ λ΄κ³  μλ(λΉμ΄μλ) Optional κ°μ²΄λ₯Ό μ»μ΄μ΄. μ΄ λΉ κ°μ²΄λ Optional λ΄λΆμ μΌλ‘ λ―Έλ¦¬ μμ±ν΄λμ μ±κΈν΄ μΈμ€ν΄μ€
            ```java
            Optional<Member> maybeMember = Optional.empty();
            ```
           
        2. Optional.of(value) : null μ΄ μλ κ°μ²΄λ₯Ό λ΄κ³  μλ κ°μ²΄ μμ±. null μ΄ λμ΄μ¬ κ²½μ° NPE λ₯Ό λμ§κΈ° λλ¬Έμ **μ£Όμ**
            ```java
            Optional<Member> maybeMember = Optional.of(aMember);
            ```
           
        3. Optional.ofNullable(value) : nullμΈμ§ μλμ§ νμ ν  μ μλ κ°μ²΄λ₯Ό λ΄κ³  μλ Optional κ°μ²΄λ₯Ό μμ±ν©λλ€.
            ```java
            Optional<Member> maybeMember = Optional.ofNullable(aMember);
            Optional<Member> maybeNotMember = Optional.ofNullable(null);
            ```
            - Optional.empty(), Optional.ofNullable(value) μ ν©μ³λμ λ©μλλΌκ³  μκ°νλ©΄ λ¨. 
            - nullμ΄ λμ΄μ¬ κ²½μ°, NPEλ₯Ό λμ§μ§ μκ³  Optional.empty()μ λμΌνκ² λΉμ΄ μλ Optional κ°μ²΄λ₯Ό μ»μ΄μ΅λλ€.
            - ν΄λΉ κ°μ²΄κ° nullμΈμ§ μλμ§ μμ μ΄ μλ μν©μμλ μ΄ λ©μλλ₯Ό μ¬μ©νμμΌ ν©λλ€.
            
3. Optionalμ΄ λ΄κ³  μλ κ°μ²΄ μ κ·ΌνκΈ°
    - Optional ν΄λμ€λ λ΄κ³  μλ κ°μ²΄λ₯Ό κΊΌλ΄μ€κΈ° μν΄μ λ€μν μΈμ€ν΄μ€ λ©μλλ₯Ό μ κ³΅ν©λλ€
    - μλ λ©μλλ€μ λͺ¨λ Optionalμ΄ λ΄κ³  μλ κ°μ²΄κ° μ‘΄μ¬ν  κ²½μ° λμΌνκ² ν΄λΉ κ°μ λ°νν©λλ€
    _ **λ€λ§** Optionalμ΄ λΉμ΄μλ κ²½μ°(μ¦, nullμ λ΄κ³  μλ κ²½μ°), λ€λ₯΄κ² μλν©λλ€. λ°λΌμ κ·Έ λΆλΆλ§ μ€λͺμ νμλ©΄
       
        ```java
        Account account = new Account("A");
        Account account_admin = new Account("Admin");
        Account account_null = null;
        ```
       μ λ μ½λλ₯Ό μμ±νλ€κ³  κ°μ 
       
        ```java
        get()
      
        maybeAccount.get().getName()
        ```
        λΉμ΄μλ Optional κ°μ²΄μ λν΄μ, NoSuchElementExceptionμ λμ§λλ€.
        
        ```java
        orElse(T other)
      
        emptyAccount.orElse(account_admin).getName() => account_admin λ°ν 
        ```
        λΉμ΄μλ Optional κ°μ²΄μ λν΄μ, λμ΄μ¨ μΈμλ₯Ό λ°νν©λλ€.
        
        ```java
        orElseGet(Supplier<? extends T> other)
      
        emptyAccount.orElseGet(()->
            new Account("new Account")
        ).getName()
        ```
        λΉμ΄μλ Optional κ°μ²΄μ λν΄μ, λμ΄μ¨ ν¨μν μΈμλ₯Ό ν΅ν΄ μμ±λ κ°μ²΄λ₯Ό λ°νν©λλ€.
        orElse(T other)μ κ²μΌλ₯Έ λ²μ μ΄λΌκ³  λ³΄μλ©΄ λ©λλ€. λΉμ΄μλ κ²½μ°μλ§ ν¨μκ° νΈμΆλκΈ° λλ¬Έμ orElse(T other) λλΉ μ±λ₯μ μ΄μ μ κΈ°λν  μ μμ΅λλ€.
        
        ```java
        orElseThrow(Supplier<? extends X> exceptionSupplier)
      
        emptyAccount.orElseThrow(() ->
            new RuntimeException("error")
        )
        ```
        λΉμ΄μλ Optional κ°μ²΄μ λν΄μ, λμ΄μ¨ ν¨μν μΈμλ₯Ό ν΅ν΄ μμ±λ μμΈλ₯Ό λμ§λλ€.
        
Optionalμ μλͺ»λ μ¬μ©
-
μμμ μ€λͺλλ¦° κ² μ²λΌ get() λ©μλλ λΉμ΄μλ Optional κ°μ²΄λ₯Ό λμμΌλ‘ νΈμΆν  κ²½μ°, μμΈλ₯Ό λ°μμν€λ―λ‘ λ€μκ³Ό κ°μ΄ κ°μ¬ μ‘΄μ¬ μ¬λΆλ₯Ό bool νμμΌλ‘ λ°ννλ isPresent()λΌλ λ©μλλ₯Ό ν΅ν΄ null μ²΄ν¬κ° νμν©λλ€.

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

κ°μ μ½λλ₯Ό λ€μ Optional μμ΄ μμ±ν΄λ³΄κ² μ΅λλ€.

```java
String text = getText();
int length;
if (text != null) {
	length = maybeText.get().length();
} else {
	length = 0;
}
```

Optionalμ μ¬μ©νλ €λ μ΄μ λ μμμ μ€λͺλλ Έλ κ² μ²λΌ κ³ ν΅μ€λ¬μ΄ null μ²λ¦¬λ₯Ό μ§μ νμ§ μκ³  **Optional ν΄λμ€μ μμ**νκΈ° μν¨ 
λ°λΌμ null μ²΄ν¬λ₯Ό νμ§ μλ κ²μ΄ μ’λ€.

λ€λ₯Έ μλͺ»λ μμ λ‘ μ΄ μ  ν¬μ€νΈμμ λ³΄μλ getCityOfMemberFromOrder() λ©μλλ₯Ό κ°μ μ€νμΌλ‘ μμ±νλ©΄ λ€μκ³Ό κ°μ΅λλ€. μ΄ μ  ν¬μ€νΈμμ λ³΄μλ μ½λμ λ³λ° λ€λ₯΄μ§ μμ μμ€μ, μ¬μ€ μ€νλ € μ΄μ§ λ λ³΅μ‘ν΄ λ³΄μ΄λ λμ°ν μ½λκ° νμνμμ΅λλ€. λ¬΄μμ΄ μ΄λμλΆν° μ΄λ»κ² μλͺ»λ κ±ΈκΉμ?

```java
/* μ£Όλ¬Έμ ν νμμ΄ μ΄κ³  μλ λμλ₯Ό λ°ννλ€ */
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

ptionalμ μ νν μ΄ν΄νκ³  μ λλ‘ μ¬μ©νμ€ μ μλ κ°λ°μλΌλ©΄ μ²«λ²μ§Έ μμ μ μ½λλ λ€μκ³Ό κ°μ΄ ν μ€μ μ½λλ‘ μμ±ν  μ μμ΄μΌ ν©λλ€. λ€μ λ§ν΄μ, κΈ°μ‘΄μ μ‘°κ±΄λ¬ΈμΌλ‘ nullμ λνλ μκ°μ ν¨μν μ¬κ³ λ‘ μμ ν μλ‘­κ² λ°κΏμΌ ν©λλ€.

```java
int length = Optional.ofNullable(getText()).map(String::length).orElse(0);
```

* map μ²λΌ μνλ ννλ‘ λ°ν ν μ΄ν, orElseμΌλ‘ nullμ²λ¦¬λ₯Ό ν΄μ£Όλ©΄ λλ€.

π Streamμ²λΌ μ¬μ©νκΈ°
-
Optionalμ μ λλ‘ μ¬μ©νλ €λ©΄, Optionalμ μ΅λ 1κ°μ μμλ₯Ό κ°μ§κ³  μλ νΉλ³ν Streamμ΄λΌκ³  μκ°ν΄μΌν¨  
Optional ν΄λμ€μ Stream ν΄λμ€ κ°μ μ§μ μ μΈ κ΅¬νμ΄λ μμκ΄κ³λ μμ§λ§ μ¬μ© λ°©λ²μ΄λ κΈ°λ³Έ μ¬μμ΄ λ§€μ° μ μ¬νκΈ° λλ¬Έμλλ€.  
Stream ν΄λμ€κ° κ°μ§κ³  μλ map()μ΄λ flatMap(), filter()μ κ°μ λ©μλλ₯Ό Optionalλ κ°μ§κ³  μμ΅λλ€. λ°λΌμ Streamμ λ₯μνκ² λ€λ£¨μλ λΆμ΄μλΌλ©΄ Optionalλ μ΄λ ΅μ§ μκ² λ€λ£¨μ€ μ μμ


<br/>

π map()μΌλ‘ λ³μ νκΈ°
-
Stream APIλ₯Ό λλ£¨λ―μ΄ Optional APIλ₯Ό μ¬μ©νμ¬ μ²« λ²μ§Έ ν¬μ€νΈμ getCityOfMemberFromOrder() λ©μλλ₯Ό λ¦¬νν λ§ ν΄λ³΄κ² μ΅λλ€.

```java
/* μ£Όλ¬Έμ ν νμμ΄ μ΄κ³  μλ λμλ₯Ό λ°ννλ€ */
public String getCityOfMemberFromOrder(Order order) {
	return Optional.ofNullable(order)
			.map(Order::getMember)
			.map(Member::getAddress)
			.map(Address::getCity)
			.orElse("Seoul");
}
```
* [optionalTest](./OptionalTest.java) => optionalTest μ€ν
* μ£Όμν  μ μ orElse νλ²λ§ μμΌλ©΄ λλ€λ μ¬μ€μ΄λ€!
* μ²« λ²μ§Έ ν¬μ€νΈμμ λ€λ£¨μλ 2κ°μ§ μ ν΅μ μΈ NPE λ°©μ΄ ν¨ν΄μ λΉν΄ ν¨μ¬ κ°κ²°νκ³  λͺνν΄μ§ μ½λλ₯Ό λ³Ό μ μμ΅λλ€. μ°μ  κΈ°μ‘΄μ μ‘΄μ¬νλ μ‘°κ±΄λ¬Έλ€μ΄ λͺ¨λ μ¬λΌμ§κ³  Optionalμ μλ €ν(fluent) APIμ μν΄μ λ¨μν λ©μλ μ²΄μ΄λμΌλ‘ λͺ¨λ λμ²΄λμμ΅λλ€. λ©μλ μ²΄μ΄λμ κ° λ¨κ³ λ³λ‘ μ’ λ μμΈν μ§μ΄λ³΄κ² μ΅λλ€.
* ofNullable() μ μ  ν©ν λ¦¬ λ©μλλ₯Ό νΈμΆνμ¬ Order κ°μ²΄λ₯Ό Optionalλ‘ κ°μΈμ£Όμμ΅λλ€.
* νΉμ Order κ°μ²΄κ° nullμΈ κ²½μ°λ₯Ό λλΉνμ¬ of() λμ μ ofNullable()μ μ¬μ©νμ΅λλ€.
* 3λ²μ map() λ©μλμ μ°μ νΈμΆμ ν΅ν΄μ Optional κ°μ²΄λ₯Ό 3λ² λ³ννμμ΅λλ€. λ§€ λ² λ€λ₯Έ λ©μλ λ νΌλ°μ€λ₯Ό μΈμλ‘ λκ²¨μ Optionalμ λ΄κΈ΄ κ°μ²΄μ νμμ λ°κΏμ£Όμμ΅λλ€. (Optional<Order> -> Optional<Member> -> Optional<Address> -> Optional<String>)
* λ§λ¬΄λ¦¬ μμμΌλ‘ orElse() λ©μλλ₯Ό νΈμΆνμ¬ μ΄ μ  κ³Όμ μ ν΅ν΄ μ»μ Optionalμ΄ λΉμ΄μμ κ²½μ°, λν΄νΈλ‘ μ¬μ©ν  λμ μ΄λ¦μ μΈνν΄μ£Όκ³  μμ΅λλ€.
* μ΄λ μ κ°μ? Optionalλ₯Ό μ λλ‘ νμ©νμ¬ μ²μμΌλ‘ null-safeν μ½λλ₯Ό μμ±ν΄λ³΄μμ΅λλ€. μ΄ μ μ Streamμ μ¬μ©νμ¬ μ΄λ° μμΌλ‘ μ½λ©μ ν΄λ³΄μ  μ μ΄ μμΌμλ€λ©΄ λ§μ΄ λ―μ€ μλ μμ΅λλ€. Java8μ λλ€μκ³Ό λ©μλ λ νΌλ°μ€λ₯Ό μ’μνμ λ€λ©΄ μ΄ μ½λκ° λ§μμ λμ€ κ²λλ€. :smile:


<br/>


π filter()λ‘ λ λ²¨μ
-

* Java8 μ΄ μ μ NPE λ°©μ§λ₯Ό μν΄μ λ€μκ³Ό κ°μ΄ null μ²΄ν¬λ‘ μμνλ if μ‘°κ±΄λ¬Έ ν¨ν΄μ μμ£Ό λ³΄μ¨μ κ²λλ€.

```java
if (obj != null && obj.do() ...)
```

* μλ₯Ό λ€μ΄, μ£Όμ΄μ§ μκ°(λΆ) λ΄μ μμ±λ μ£Όλ¬Έμ ν κ²½μ°μλ§ ν΄λΉ νμ μ λ³΄λ₯Ό κ΅¬νλ λ©μλλ₯Ό μ ν¨ν΄μΌλ‘ μμ±ν΄λ³΄μμ΅λλ€.


```java
public Member getMemberIfOrderWithin(Order order, int min) {
	if (order != null && order.getDate().getTime() > System.currentTimeMillis() - min * 1000) {
		return order.getMember();
	}
}

```

* μ μ½λλ if μ‘°κ±΄λ¬Έ λ΄μ null μ²΄ν¬μ λΉμ§λμ€ λ‘μ§μ΄ νΌμ¬λμ΄ μμ΄μ κ°λμ±μ΄ λ¨μ΄μ§λλ€. κ²λ€κ° nullμ λ¦¬ν΄ν  μ μκΈ° λλ¬Έμ λ©μλ νΈμΆλΆμ NPE μνμ μ ννκ³  μμ΅λλ€.

* λ°λ©΄μ filter() λ©μλλ₯Ό μ¬μ©νλ©΄ if μ‘°κ±΄λ¬Έ μμ΄ λ©μλ μ°μ νΈμΆλ§μΌλ‘λ μ’ λ μ½κΈ° νΈν μ½λλ₯Ό μμ±ν  μ μμ΅λλ€. λΏλ§ μλλΌ, λ©μλμ λ¦¬ν΄ νμμ Optionalλ‘ μ¬μ©ν¨μΌλ‘μ¨ νΈμΆμμκ² ν΄λΉ λ©μλκ° nullμ λ΄κ³  μλ Optionalμ λ°νν  μλ μλ€λ κ²μ λͺμμ μΌλ‘ μλ €μ£Όκ³  μμ΅λλ€.

```java
public Optional<Member> getMemberIfOrderWithin(Order order, int min) {
	return Optional.ofNullable(order)
			.filter(o -> o.getDate().getTime() > System.currentTimeMillis() - min * 1000)
			.map(Order::getMember);
}
```

* filter() λ©μλλ λμ΄μ¨ ν¨μν μΈμμ λ¦¬ν΄ κ°μ΄ falseμΈ κ²½μ°, Optionalμ λΉμλ²λ¦¬λ―λ‘ κ·Έ μ΄ν λ©μλ νΈμΆμ μλ―Έκ° μμ΄μ§κ² λ©λλ€. 
* Stream ν΄λμ€μ filter() λ©μλμ λμ λ°©μμ΄ λμΌνμ§λ§, Optionalμ κ²½μ° μμκ° νλ λ°μ μκΈ° λλ¬Έμ μ΄λ° ν¨κ³Όκ° λνλκ² λ©λλ€.


<br/>

π Java8 μ΄ μ μ κ°λ°λ μ½λλ₯Ό Optionalνκ² λ°κΎΈκΈ°
-
* Java8 μ΄ μ μ κ°λ°λ APIλ€μ μνκΉκ²λ λΉμμ Optional ν΄λμ€κ° μμκΈ° λλ¬Έμ null-safeνμ§ μμ΅λλ€. 
* μ¬μ§μ΄ Java νμ€ API μ‘°μ°¨ νμ νΈνμ±μ λ³΄μ₯μ μν΄μ κΈ°μ‘΄ APIμ Optional ν΄λμ€λ₯Ό μ μ©ν  μ μμμ΅λλ€. λ€νμ΄λ μ°λ¦¬λ μ€μ€λ‘ Optional ν΄λμ€λ₯Ό μ¬μ©νμ¬ κΈ°μ‘΄ μ½λκ° null-safeνλλ‘ λ°κΏμ€ μ μμ΅λλ€.


<br/>

π λ©μλμ λ°νκ°μ΄ μ‘΄μ¬νμ§ μμ λ μ ν΅μ μΈ μ²λ¦¬ ν¨ν΄
-
* μ΄μ  κ°λ°λ λ©μλλ€μ λ°νν  κ°μ΄ μ‘΄μ¬νμ§ μμ κ²½μ°, ν¬κ² 2κ°μ§ ν¨ν΄μΌλ‘ μ²λ¦¬νμμ΅λλ€. κ° μ²λ¦¬ ν¨ν΄μ μ΄λ»κ² κ°μ ν  μ μλμ§ μμ  μ½λλ₯Ό ν΅ν΄ μ΄ν΄λ³΄κ² μ΅λλ€.


1. null λ°ν
    * Map μΈν°νμ΄μ€μ get() λ©μλλ μ£Όμ΄μ§ μΈλ±μ€μ ν΄λΉνλ κ°μ΄ μμΌλ©΄ nullμ λ°νν©λλ€.

    ```java
    Map<Integer, String> cities = new HashMap<>();
    cities.put(1, "Seoul");
    cities.put(2, "Busan");
    cities.put(3, "Daejeon");
    ```

    * λ°λΌμ ν΄λΉ APIλ₯Ό μ¬μ©νλ μ½λλ₯Ό null-safeνκ² λ§λ€κΈ° μν΄μ null μ²΄ν¬λ₯Ό ν΄μ€μΌ ν©λλ€.
    
    ```java
    String city = cities.get(4); // returns null
    int length = city == null ? 0 : city.length(); // null check
    System.out.println(length);
    ```

    * λ€μκ³Ό κ°μ΄ get() λ©μλμ λ°ν κ°μ Optionalλ‘ κ°μΈμ£Όλ©΄, μμ°μ€λ½κ² null-safeν μ½λκ° λ©λλ€.

    ```java
    Optional<String> maybeCity = Optional.ofNullable(cities.get(4)); // Optional
    int length = maybeCity.map(String::length).orElse(0); // null-safe
    System.out.println("length: " + length);
    
    ```
    * μ½κΈ° μ¬μ΄ μ½λλ₯Ό μμ±νκΈ° μν΄ map()κ³Ό orElse() λ©μλκ° μ΄λ»κ² μ¬μ©λκ³  μλμ§ μ£ΌμκΉκ² λ³΄μκΈ° λ°λλλ€. λμ λ¬Έμμ΄μ κΈΈμ΄λ‘ λ³ννκ³  λν΄νΈ κ°μ μ€μ ν΄μ£Όλ κ³Όμ μ νλμ νμνκΈ° νΈνμ§ μμΌμ κ°μ?



2. μμΈ λ°μ
    * λλ²μ§Έ ν¨ν΄μ nullμ λ°ννμ§ μκ³  μμΈλ₯Ό λμ λ²λ¦¬λ κ²½μ°μλλ€. List μΈν°νμ΄μ€μ get() λ©μλλ μ£Όμ΄μ§ μΈλ±μ€μ ν΄λΉνλ κ°μ΄ μμΌλ©΄ ArrayIndexOutOfBoundsExceptionμ λμ§λλ€.

    ```java
    List<String> cities = Arrays.asList("Seoul", "Busan", "Daejeon");
    ```

    * λ°λΌμ, λ€μκ³Ό κ°μ΄ try-catch κ΅¬λ¬Έμ μ¬μ©νμ¬ μμΈ μ²λ¦¬λ₯Ό ν΄μ€μΌ νλ©°, μμΈ μ²λ¦¬ νμλ null checkλ ν΄μ€μΌ νκΈ° λλ¬Έμ μ½λκ° μ§μ λΆν΄μ§λλ€.

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

    * μ΄λ° κ²½μ°, λ€μκ³Ό κ°μ΄ μμΈ μ²λ¦¬λΆλ₯Ό κ°μΈμ μ μ  μ νΈλ¦¬ν° λ©μλλ‘ λΆλ¦¬ν΄μ£Όλ©΄ μ’μ΅λλ€. Optional ν΄λμ€μ μ μ  ν©ν λ¦¬ λ©μλλ₯Ό μ¬μ©νμ¬ μ μ μ²λ¦¬ μμ μμΈ μ²λ¦¬ μμ λ°νν  Optional κ°μ²΄λ₯Ό κ°κ° μ§μ ν΄μ£Όμμ΅λλ€. μ΄ κ²½μ°μλ Optionalμ λ΄μ κ°μ²΄κ° nullμΈμ§ μλμ§ νμ€ν μ μ μκΈ° λλ¬Έμ Optional.ofNullable() λμ μ λ€λ₯Έ 2κ°μ μ μ  ν©ν λ¦¬ λ©μλλ₯Ό μΈ μ μλ€λ μ μ μ£ΌμκΉκ² λ³΄μκΈ° λ°λλλ€.
    
    ```java
    public static <T> Optional<T> getAsOptional(List<T> list, int index) {
        try {
            return Optional.of(list.get(index));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
    ```
    * μλμ κ°μ΄ μ μ  μ νΈλ¦¬ν° λ©μλλ₯Ό ν΅ν΄ Optional κ°μ²΄λ₯Ό νλ³΄ νμ null-safeνκ² μ½λ©ν  μ μμ΅λλ€.
    
    ```java
    Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
    int length = maybeCity.map(String::length).orElse(0); // null-safe
    System.out.println("length: " + length);
    ```


ifPresent() λ©μλ
-
* μ, μ΄ μ  ν¬μ€νΈμμ λ―Έμ² μκ°λλ¦¬μ§ μμ μ‘°κΈ νΉλ³ν λ©μλκ° νλ μμ΅λλ€. 
* λ°λ‘ ifPresent() λ©μλμΈλ°μ. λ§μ λΆλ€μ΄ isPresent()μ νΌλνκΈ°λ νλ λμμλλ€.

* ifPresent(Consumer<? super T> consumer): μ΄ λ©μλλ νΉμ  κ²°κ³Όλ₯Ό λ°ννλ λμ μ Optional κ°μ²΄κ° κ°μΈκ³  μλ κ°μ΄ μ‘΄μ¬ν  κ²½μ°μλ§ μ€νλ  λ‘μ§μ ν¨μν μΈμλ‘ λκΈΈ μ μμ΅λλ€.
* ν¨μν μΈμλ‘ λλ€μμ΄λ λ©μλ λ νΌλ°μ€κ° λμ΄μ¬ μ μλλ°μ. λ§μΉ λΉλκΈ° λ©μλμ μ½λ°± ν¨μμ²λΌ μλν©λλ€. λ°λ‘ μ  μμ μ μ½λλ₯Ό ifPresent() λ©μλλ₯Ό μ΄μ©ν΄μ μ¬μμ±νλ©΄ λ€μκ³Ό κ°μ΅λλ€

```java
Optional<String> maybeCity = getAsOptional(cities, 3); // Optional
maybeCity.ifPresent(city -> {
	System.out.println("length: " + city.length());
});
```

















        
    
    
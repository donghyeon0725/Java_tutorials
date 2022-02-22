# java 제네릭 메소드

**제네릭 메소드**는 메소드의 선언 부에 적은 제네릭으로 리턴 타입, 파라미터의 타입이 정해지는 메소드이다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7035b373-c167-4739-a067-6064952715c1/Untitled.png)

즉, <String>getName(”hi”); 로 호출하면 해당 파라미터도 String 이 되어야 하고 리턴타입도 String 이 된다.

### 클래스 제네릭과 차이점

---

> 클래스 제네릭
>

```jsx
public class Class<T> {
    
		// 예외
    static T getName(T name) {
        return name;
    }

		// 정상
		T getName(T name) {
        return name;
    }
}
```

클래스에 제네릭 타입을 사용한 경우 static 메소드는 매개변수에 해당 타입을 사용할 수 없다. 이는 static 메소드의 경우 로딩 시점에 메모리에 올라가야 하는데 name 의 타입이 정해지지 않았기 때문이다.

다만 인스턴스 필드인 경우에는 호출 시점에 타입이 정해지기 때문에 제네릭 매개변수를 사용할 수 있다.

다만 이는 클래스 선언시점에 타입이 정해진다.

> 메소드 제네릭
>

```jsx
public class Method {
		// 정상
    static <T> T getName(T name) {
        return name;
    }

		// 정상
    <T> T getTitle(T title) {
        return title;
    }
}
```

그러나 메소드 제네릭의 경우 호출 시점에 타입을 정하기 때문에 static 사용이 가능하다.

### 주의점

---

```jsx
public class Method<T> {
		// 정상
    static <T> T getName(T name) {
        return name;
    }

		// 정상
    <T> T getTitle(T title) {
        return title;
    }

		// 정상
		T getContent(T content) {
        return content;
    }
}
```

여기서 클래스에 사용한 제네릭 T와 메소드에 사용한 제네릭 T는 완전히 다른 타입이다.

마치 클래스 인스턴스의 변수와 지역변수의 차이처럼 말이다.

다만 getContent 메소드는 제네릭 메소드가 아니기 때문에 T가 클래스의 T를 의미한다고 할 수 있다.

⇒ 제네릭 메소드를 사용하면 T가 지역변수로 바뀐다.

### 제네릭의 다양한 사용

---

제네릭은 타입을 제한하기 위해서 사용하는 만큼 다양한 문법을 지워냏서 제안할 수 있다.

```jsx
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

이 제네릭의 문법을 분석하면 다음과 같다.

```jsx
<T extends Comparable<T>>
```

- Comparable 인터페이스를 구현한 클래스 타입이어야 한다는 조건이다.

```jsx
<? super T>
```

T  의 조상클래스 또는 같은 클래스만 ? 에 올 수 있다는 뜻이다. 즉 제네릭으로 사용할 수 있는 클래스는 Comparable 인터페이스를 구현한 클래스이긴 해야하는데 이 때 Comparable의 제네릭 타입으로는 T의 조상 또는 같은 클래스를 넘겨준 경우에만 가능하다

이런 것을 바운드 와일드카드 타입이라고 부른다.

바운드 와일드카드 타입에는 다음과 같이 세가지 타입이 있다.

- 무공변
    - 오로지 자기 타입만 허용하는 T
- 공변
    - 구체적인 방향으로 타입 변환을 허용하는 것 (자기 자신과 자식 객체만 허용) <? extends T>
- 반공변
    - 추상적인 방향으로의 타입 변환을 허용하는 것 (자기 자신과 부모 객체만 허용) <? super T>

> 무공변
>

```jsx
List<String> strs = new ArrayList<>();
List<Object> objs = strs;
String s = strs.get(0); // ClassCastException
```

이는 컴파일 예외를 발생 시킨다. 런타입 안정성을 보장하기 위해서 이런 방식을 택했는데 문제는 다음과 같은 경우에 있다.

```jsx
interface Collection<E> {
  void addAll(Collection<E> items);  
}

void copyAll(Collection<Object> to, Collection<String> from) {
  to.addAll(from); // Compile Error
}
```

위 코드는 from 로 받은 Collection<Object> 타입을 그대로 to 에 추가하고 싶은 것이다.

그런데 무공변은 오직 자기 타입만 허용하기 때문에 to 와 from 은 엄연히 다른 타입 취급한다.

String은 Object의 서브타입이지만

Collection<String> 은 Collection<Object> 의 서브타입이 아니기 때문이다.

이 코드를 공변을 사용해서 컴파일 될 수 있게 할 수 있다.

> 공변
>

```jsx
public interface Collection<E> {
    void addAll(Collection<? extends E> items);
}
```

이렇게 하면 위 코드를 컴파일 할 수 있다.

> 반 공변
>

조상 클래스만 허용하는 것. 예를 들어서 세 클래스가 있는데 Number > Math > Study 즉, Math는 Study의 조상 클래스이고 Number는 Math 의 조상 클래스라고 하자.

아래와 같이 받은 리스트에 값을 넣어주고 해당 값을 그대로 리턴하는 클래스가 있다고 가정할 때 어떤 타입이 list 에 올 수 있는지 주목해보자.

```jsx
<T extends Number> T addToListAndReturnGotValeu(List<? super T> list, T t) {
    list.add(t);
    return t;
}
```

```jsx
List<Number> numbers = new ArrayList<>();
List<Math> maths = new ArrayList<>();
List<Study> studies = new ArrayList<>();

1. method.<Math>addToListAndReturnGotValeu(numbers, new Math());
2. method.<Math>addToListAndReturnGotValeu(maths, new Math());
3. method.<Math>addToListAndReturnGotValeu(studies, new Math()); // 예외
4. method.<Other>addToListAndReturnGotValeu(studies, new Math()); // 예외
```

Number 는 Math 의 조상 클래스이기 때문에 1번은 가능하다. 그리고 Math 클래스 자체도 가능하다.

그런데 Study는 Math의 하위 클래스이고 조상이 아니기 때문에 안된다.

Other 클래스는 아무런 클래스도 상속 받지 않은 클래스로 메소드의 제네릭 타입 위치에 올 수 없다.

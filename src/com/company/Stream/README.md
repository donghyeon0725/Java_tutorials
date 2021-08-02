Stream
-
* 여러가지 데이터 처리 연산을 지원하기 위해서 소스(자료형)에서 추출한 연속된 데이터
* 이때 데이터 처리란
    - map
    - filter
    - reduce
    - find
    - match
    - sort 등등

<br/>
    
📌 데이터 처리 함수
-
1. stream
    - stream을 뽑아낼 때 사용하는 함수
    
2. collect
    - stream을 특정 자료형으로 변환할 때 사용하는 함수 
    - 매개변수로 넘겨주는 값에 따라 다른 자료형으로 형 변환이 가능함
        - Collectors.toList() => List로 변환
        - Collectors.toSet() => Set으로 변환 (중복제거에 유용하게 쓰임)
3. map    
    - 매개 변수로 받은 함수가 리턴하는 타입을 모은 stream 받환
    - mapToInt : 매개변수가 int를 리턴할 때, 자료형의 모든 값을 Int로 변환할 수 있음~~~~

4. forEach
    - stream을 반복해서 매개변수로 받은 함수에 해당하는 동작을 해줌 
 
5. allMatch
    - 자료형을 반복하면서 매개변수로 전달해준 함수가 리턴한 boolean 타입에 따라 결과가 다름
    - 모든 값이 true일 때 true 리턴
    
6. anyMatch
    - 자료형을 반복하면서 매개변수로 전달해준 함수가 리턴한 boolean 타입에 따라 결과가 다름
    - 하나라도 true일 때 true 리턴
    
7. noneMatch
    - 자료형을 반복하면서 매개변수로 전달해준 함수가 리턴한 boolean 타입에 따라 결과가 다름
    - 모든 값이 false일 때 true 리턴

* 실행 : [_Stream.java](./_Stream.java)


📌 Collector 기본 사용법
-
* 실행 : [_Stream_Collector.java](./_Stream_Collector.java)

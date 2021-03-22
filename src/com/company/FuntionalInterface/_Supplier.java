package com.company.FuntionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class _Supplier {

    /**
     * 매개변수는 없고 리턴타입만 있는 거의 상수(똑같진 않음)와 같은 역할을 함, 랜덤값을 리턴하는 것으로도 사용이 가능하겠다.
     * */
    static Supplier<String> getDBConnectionUrlSupplier = () -> "jdbc://localhost:5432/user";

    /**
     * 위와 동일한 메소드
     * */
    static String getDBConnectionUrl() {
        return "jdbc://localhost:5432/user";
    }

    /**
     * Supplier 테스트
     * */
    static void SupplierGetTest() {
        System.out.println(getDBConnectionUrl());
        System.out.println(getDBConnectionUrlSupplier.get());
    }

    /**
     * Supplier 응용
     * */
    static Supplier<List<String>> getDBConnectionUrlsSupplier = () -> Arrays.asList(
      "jdbc://localhost:5432/user"
      ,"jdbc://localhost:5432/customer"
    );

    /**
     * Supplier 응용 테스트
     * */
    static void supplierApply() {
        getDBConnectionUrlsSupplier.get().forEach(System.out::println);
        // jdbc://localhost:5432/user
        // jdbc://localhost:5432/customer
    }
}

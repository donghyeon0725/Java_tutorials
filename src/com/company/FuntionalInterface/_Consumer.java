package com.company.FuntionalInterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
    }

    /**
     * 매개변수만 있고 리턴타입은 없을 때 사용
     * */
    static Consumer<Customer> greetCustomerConsumer = customer -> System.out.println("Hello " + customer.customerName
                                                    + ", thanks for registering phone number " + customer.customerPhoneNumber);
    /**
     * 위 함수형 인터페이스와 동일
     * */
    static void greetCustomer(Customer customer) {
        System.out.println("Hello "
                + customer.customerName
                + ", thanks for registering phone number "
                + customer.customerPhoneNumber);
    }

    /**
     * 함수형 인터페이스를 사용해서 호출하는 예제
     * */
    static void consumerTest() {
        Customer maria = new Customer("Maria", "999999");
        // Normal java function
        greetCustomer(maria);

        // Consumer Functional interface
        greetCustomerConsumer.accept(maria);
    }

    /**
     * 매개변수를 2개 받는 경우
     * */
    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = ((customer, showPhoneNumber) ->
            System.out.println(
                    "Hello "
                    + customer.customerName
                    + ", thanks for registering phone number "
                    + (showPhoneNumber ? customer.customerPhoneNumber : "******")
            ));

    /**
     * BiConsumer<Customer, Boolean> 테스트
     * */
    public static void greetCustomerConsumerV2Test() {
        Customer maria = new Customer("Maria", "999999");
        greetCustomerConsumerV2.accept(maria, false); //Hello Maria, thanks for registering phone number ******
    }




    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}

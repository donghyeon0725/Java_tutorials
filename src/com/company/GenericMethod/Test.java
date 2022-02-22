package com.company.GenericMethod;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Method method = new Method();

        List<Number> numbers = new ArrayList<>();
        List<Math> maths = new ArrayList<>();
        List<Study> studies = new ArrayList<>();

        method.<Math>addToListAndReturnGotValeu(numbers, new Math());
        method.<Math>addToListAndReturnGotValeu(maths, new Math());
//        method.<Math>addToListAndReturnGotValeu(studies, new Math()); 예외

    }
}

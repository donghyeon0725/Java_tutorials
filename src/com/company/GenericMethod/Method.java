package com.company.GenericMethod;

import java.util.List;

public class Method {
    static <T> T getName(T name) {
        return name;
    }

    <T> T getTitle(T title) {
        return title;
    }

    <T extends Comparable<? super T>> T getObject(T object) {
        return object;
    }

    <T> T addToListAndReturnGotValeu(List<? super T> list, T t) {
        list.add(t);
        return t;
    }

}

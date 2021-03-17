package com.company.MethodReference;

public class MemberVO {
    public static boolean isClassOf(Object obj) {
        return obj.getClass() == MemberVO.class;
    }
}

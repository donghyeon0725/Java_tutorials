package com.company.Static;

public class Static {
    /**
     * 스태틱 필드는 모든 인스턴스가 공통으로 가지는 값이다.
     * */
    private static String staticStr = "defualt";
    private String noneStaticStr;

    /**
     * 스태틱 메소드가 생성된 시점에 인스턴스 필드는 없을 것이므로 오직 스태틱 필드에만 접근이 가능하다.
     * */
    public static String getStaticStrWithStatic() {
        return staticStr;
    }

    public static void setStaticStrWithStatic(String str) {
        staticStr = str;
    }

    /**
     * 인스턴스의 경우 인스턴스 필드와 스태틱 필드 모두에 접근이 가능하다.
     * */
    public String getStaticStr() {
        return staticStr;
    }

    public void setStaticStr(String staticStr) {
        Static.staticStr = staticStr;
    }

    public String getNoneStaticStr() {
        return noneStaticStr;
    }

    public void setNoneStaticStr(String noneStaticStr) {
        this.noneStaticStr = noneStaticStr;
    }
}

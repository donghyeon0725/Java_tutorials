package com.company.passBy;

public class PassByTest {

    static class DTO {
        private String name;

        public String getName() {
            return name;
        }

        public DTO setName(String name) {
            this.name = name;
            return this;
        }
    }
    public static void main(String[] args) {
        int a = 5;
        String b = "old";
        DTO c = new DTO();
        c.setName("name");
        DTO d = new DTO();
        d.setName("name");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c.getName());
        System.out.println(d.getName());

        System.out.println("==========");

        passbyTest(a, b, c, d);

        System.out.println("==========");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c.getName());
        System.out.println(d.getName());
    }

    public static void passbyTest(int a, String b, DTO c, DTO d) {
        System.out.println("passby method start");
        a = 10;
        b = "new";
        c.setName("change");
        d = new DTO();
        d.setName("change");

        System.out.println(a);
        System.out.println(b);
        System.out.println(c.getName());
        System.out.println(d.getName());
    }
}

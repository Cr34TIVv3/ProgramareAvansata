package project.test;

import org.testng.annotations.Test;

public class Test1 {
    final private String member1;
    final private Integer member2;

    public Test1(String member1, Integer member2) {
        this.member1 = member1;
        this.member2 = member2;
    }

    @Test
    public static void staticMethod1() {
        System.out.println("hello world");
    }

    public void showInfo() {
        System.out.println(member1 + " " + member2);
    }
}

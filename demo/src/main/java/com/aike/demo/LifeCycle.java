package com.aike.demo;

public class LifeCycle {
    public LifeCycle() {
        System.out.println("构造方法初始化");
    }
    private String field = getField();
    {
        System.out.println(field);
    }
    private static String staticField = getStaticField();

    static {
        System.out.println(staticField);
        System.out.println("静态代码块");
    }
    public static String getStaticField() {
        String statiFiled = "静态属性初始化";
        return statiFiled;
    }
    public static String getField() {
        String filed = "代码块初始化";
        return filed;
    }
    public static void main(String[] argc) {
        new LifeCycle();
    }
}
package com.aike.newfeatures.lambda;


import java.util.ArrayList;
import java.util.List;

/**
 * Lambda 表达式 − Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。
 * 方法引用 − 方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 */
public class Client {
    final static String HELLO = "hello ";
    public static void main(String[] args) {
        MathOperation add = (int a, int b) -> a + b;
        MathOperation sub = (a, b) -> {return a - b;};
        MathOperation mul = (a, b) -> a * b;
        MathOperation div = (a, b) -> a / b;

        Client client = new Client();
        System.out.println(client.operate(3, 1, add));
        System.out.println(client.operate(3, 1, (a, b) -> a / b));
        System.out.println(client.operate(3, 1, sub));
        System.out.println(client.operate(3, 1, mul));
        System.out.println(client.operate(3, 1, div));

        GreetingService greetingService = message -> System.out.println(HELLO + message);
        greetingService.sayMessage("google");
        //方法引用使用一对冒号 :: 。
        List<String> list = new ArrayList<>();
        list.add("ok");
        list.forEach(greetingService::sayMessage);

    }
    //函数式接口（可以不用注解修饰）
    @FunctionalInterface
    interface MathOperation{
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a,int b, MathOperation operation){
        return operation.operation(a,b);
    }
}

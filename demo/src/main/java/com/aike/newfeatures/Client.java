package com.aike.newfeatures;

public class Client {
    public static void main(String[] args) {
        GreetingService greetingService = message -> System.out.println("nihao"+message);
        greetingService.sayMessage("hello");
    }
}

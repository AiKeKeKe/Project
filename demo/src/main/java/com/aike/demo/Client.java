package com.aike.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Client::get);
        System.out.println(future.get(6, TimeUnit.SECONDS));
        System.out.println(1);
    }

    static Integer get(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }
}

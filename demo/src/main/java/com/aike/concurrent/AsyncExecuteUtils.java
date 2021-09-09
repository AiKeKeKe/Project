package com.aike.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 通用异步执行的公共类
 * Java 8 强大的函数式异步编程辅助类:CompletableFuture
 */
public class AsyncExecuteUtils<T> {

    /**
     * 对集合中的每个对象做异步转换
     */
    public static <R, T> List<R> concurrentMap(List<T> list, Function<T, R> mapper, ExecutorService executorService) {
        List<CompletableFuture<R>> futures = list.stream()
                .map(entity -> CompletableFuture.supplyAsync(() -> mapper.apply(entity), executorService))
                .collect(Collectors.toList());
        return futures.stream().map(future -> future.join()).collect(Collectors.toList());
    }

    /**
     * 对集合里的每一个对象做异步执行
     */
    public static <T> void concurrentRun(List<T> list, Consumer<T> consumer, ExecutorService executorService){
        CompletableFuture[] futures = list.stream()
                .map(entity -> CompletableFuture.runAsync(() -> consumer.accept(entity), executorService))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }

    /**
     * 对单个对象做异步执行
     */
    public static <R> void runAsync(R r, Consumer<R> consumer, ExecutorService executorService){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> consumer.accept(r), executorService);
        future.join();
    }
}

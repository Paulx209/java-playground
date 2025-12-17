package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo02 {

    /**
     * // 不带返回值的异步请求，默认线程池
     * public static CompletableFuture<Void> runAsync(Runnable runnable)
     *
     * // 不带返回值的异步请求，可以自定义线程池
     * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test01();
    }
    public static void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            //代码逻辑
            System.out.println("do someThing");
        });

        //等待任务执行完成
        System.out.println(cf.get());
    }

    public static void test02() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            //代码逻辑
            System.out.println("do someThing");
        },executorService);

        //等待任务执行完成
        System.out.println(cf.get());
    }
}

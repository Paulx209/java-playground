package com.sonicge.testCompletableFuture;

import java.util.concurrent.*;

public class TestDemo01 {
    /**
     * // 带返回值异步请求，默认线程池
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     *
     * // 带返回值的异步请求，可以自定义线程池
     * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     *
     */
    public static void main(String[] args) {
        test2();
    }

    /**
     * supplyAsync方法 默认线程池
     */
    public static void test1(){
        //测试supplyAsync方法，支持返回值！返回值类型和CompletableFuture<T> 泛型一致
        CompletableFuture<String> cf  = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("do something...");
                Thread.sleep(2000);
                return "result";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //等待任务执行完成
        try {
            String res = cf.get(3000, TimeUnit.MILLISECONDS);
            System.out.println(res);
        } catch (InterruptedException e) {
            System.out.println(111);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println(222);
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println(333);
            throw new RuntimeException(e);
        }
    }

    public static void test2(){
        //自定义线程池类 -> ThreadPoolExecutor类
//        ThreadPoolExecutor executor = new ThreadPoolExecutor();
        //线程池工具类 ->  Executors 固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("doSomething...");
            return "result";
        }, executorService);

        try {
            String res = completableFuture.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

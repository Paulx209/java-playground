package com.sonicge.testCompletableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo06 {
    /**
     * thenAccept() 支持传递参数，但是没有返回值！也不会重新开启一个线程，和主线程一致
     * thenAcceptAsync() 支持传递参数，但是没有返回值！会重新开启一个线程，默认使用ForkJoin的线程池
     * @param args
     */
    public static void main(String[] args) {
        test02();

    }

    private static void test02() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("主线程的名称为:" + Thread.currentThread().getName());

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cf1 do something...");
            return "res";
        },executorService);

        CompletableFuture<Void> cf2 = cf1.thenAcceptAsync((result) -> {
            String result2 = result + "ult";
            System.out.println(Thread.currentThread().getName() +  " " +result2);
        },executorService);

        try {
            String res1 = cf1.get();
            Void res2 = cf2.get();
            System.out.println(res1);
            System.out.println(res2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("主线程的名称为:" + Thread.currentThread().getName());

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cf1 do something...");
            return "res";
        },executorService);

        CompletableFuture<Void> cf2 = cf1.thenAccept((result1) -> {
            String result2 = result1 + "ult";
            //和主线程是一致的！因此不会重新开启一个线程，也就是不会异步。因此就不需要线程池参数
            System.out.println(Thread.currentThread().getName() + " cf2 do something ... " );
            System.out.println(result2);
        });

        try {
            String res1 = cf1.get();
            Void res2 = cf2.get();
            System.out.println(res1);
            System.out.println(res2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

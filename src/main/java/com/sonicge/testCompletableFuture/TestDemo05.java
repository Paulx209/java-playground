package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo05 {
    /**
     * thenRun()方法 不支持参数，返回值！不会开启一个新线程，和主线程一致！
     * thenRunAsync()方法 不支持参数，返回值！会开启一个新线程，可以传递自定义的线程池
     * @param args
     */
    public static void main(String[] args) {
        test02();
    }
    public static void test01(){
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cf1 do something");
            return 1;
        });

        CompletableFuture<Void> cf2 = cf1.thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + "cf2 do something");
        });

        try {
            System.out.println("cf1结果 -> " + cf1.get());
            System.out.println("cf1结果 -> " + cf2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test02(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " cf1 do something");
            return 1;
        });

        CompletableFuture<Void> cf2 = cf1.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " cf2 do something");
        });

        try {
            System.out.println("cf1结果 -> " + cf1.get());
            System.out.println("cf1结果 -> " + cf2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

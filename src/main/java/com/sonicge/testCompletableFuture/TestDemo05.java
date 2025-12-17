package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo05 {
    /**
     * thenRun()方法 和 thenRunAsync()方法 两个不支持返回值，接收参数！
     * 但是后者是异步执行，会开启一个新的线程。需要指定线程池
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

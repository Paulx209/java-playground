package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo04 {
    /**
     * thenApply和thenApplyAsync
     * thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中，带有返回值。
     * thenApplyAsync  除了thenApply的功能以外，还支持传入自定义的线程池，并且是会重新开启一个线程执行方法。
     *
     * @param args
     */
    public static void main(String[] args) {
        test02();
    }

    /**
     * thenApplyAsync方法会异步调用，重新起一个线程
     */
    public static void test01() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something...");
            return 1;
        }, executorService);
        CompletableFuture<Integer> cf2 = cf1.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread() + "cf2 do something...");
            result += 2;
            return result;
        }, executorService);

        try {
            System.out.println("cf1的执行结果为" + cf1.get());
            System.out.println("cf2的执行结果为" + cf2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test02() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something...");
            return 1;
        });

        CompletableFuture<Integer> cf2 = cf1.thenApply((result) -> {
            System.out.println(Thread.currentThread() + "cf2 do something...");
            result += 2;
            return result;
        });

        try {
            System.out.println("cf1的执行结果为" + cf1.get());
            System.out.println("cf2的执行结果为" + cf2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

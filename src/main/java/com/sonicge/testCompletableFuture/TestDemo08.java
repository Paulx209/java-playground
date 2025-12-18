package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestDemo08 {
    /**
     * 只有等所有的任务执行完毕之后，才会继续向下执行！
     *
     * thenCombine()方法会将两个CompletableFuture任务组合起来，cfA.thenCombine(cfB, ( a, b ) -> {})，可以接收两个任务的返回值作为参数,并且可以返回值。
     *
     * thenAcceptBoth()方法会将两个CompletableFuture任务组合起来，cfA.thenCombine(cfB, ( a, b ) -> {})，可以接收两个任务的返回值作为参数,不可以返回值。
     *
     * runAfterBoth()方法不能接收返回值作为参数，也不能返回值。
     *
     */
    public static void main(String[] args) {
        test01();
    }

    /**
     * runAfterBoth
     */
    private static void test03() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something ...");
            return 1;
        }, executorService);

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf2 do something ...");
            return 2;
        }, executorService);

        CompletableFuture<Void> cf3 = cf1.runAfterBoth(cf2,() -> {
            System.out.println(Thread.currentThread() + "cf3 do something");
        });
    }

    /**
     * thenAcceptBoth
     */
    private static void test02() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something ...");
            return 1;
        }, executorService);

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf2 do something ...");
            return 2;
        }, executorService);

        CompletableFuture<Void> cf3 = cf1.thenAcceptBoth(cf2, (a, b) -> {
            int res = a + b;
            System.out.println("结果为:" + res);
            System.out.println(Thread.currentThread() + "cf3 do something");
        });
    }

    /**
     * thenCombine
     */
    private static void test01() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread() + "cf1 do something ...");
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService);

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + "cf2 do something ...");
                Thread.sleep(2000);
                return 2;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, executorService);

        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " cf3 do something ...");
            return a + b;
        });

        try {
            System.out.println("cf3结果为 -> " + cf3.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

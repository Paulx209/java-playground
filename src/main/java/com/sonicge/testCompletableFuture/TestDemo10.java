package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestDemo10 {

    /**
     * allOf()会等待所有的任务执行完成后才返回，如果有一个发生异常的话，就直接报错异常了；没有返回值，类型是Void
     *
     * anyOf()和第一个任务返回的结果或异常一致！
     *
     * @param args
     */
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " cf1 doSomething... ");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("cf1 任务完成");
            return "cf1 任务完成";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " cf2 doSomething... ");
                Thread.sleep(5000);
                System.out.println(1 / 0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("cf2 任务完成");
            return "cf2 任务完成";
        });

        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + " cf2 do something....");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf3 任务完成");
            return "cf3 任务完成";
        });

        try {
            CompletableFuture<Void> cfAll = CompletableFuture.allOf(cf1, cf2, cf3);
            System.out.println("cfAll结果 -> " +  cfAll.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

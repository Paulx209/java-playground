package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestDemo07 {
    /**
     * whenComplete()方法：上一个任务执行完成之后，可以接收到执行的结果 和 抛出的异常！正常返回的话就是异常为null，结果非null；抛出异常的话，就是异常非null，结果为null。
     *
     * whenCompleteAsync()方法，仍然是异步线程执行，可以传递一个线程池！
     *
     * handle()方法：和whenComplete()方法一样，但是可以返回结果！
     *
     * handleAsync()方法：异步。
     *
     * @param args
     */
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something ...");
            try {
                Thread.sleep(2000);
                int a = 1 / 0;
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> cf2 = cf1.handleAsync((result, ex) -> {
            System.out.println(Thread.currentThread().getName() + "结果为:" + result);
            System.out.println(Thread.currentThread().getName() + "异常为:" + ex);
            return "haha";
        });

        try {
            String s = cf2.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test01() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "cf1 do something ...");
            try {
                Thread.sleep(2000);
                int a = 1 / 0;
                return 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<Integer> cf2 = cf1.whenComplete((result, ex) -> {
            System.out.println("上个任务的结果:" + result);
            System.out.println("上个任务抛出的异常:" + ex);
            System.out.println(Thread.currentThread().getName() + "cf2 do something...");
        });

        try {
            Integer i = cf1.get();
            System.out.println(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

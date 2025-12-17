package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestDemo1 {
    public static void main(String[] args) {

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
}

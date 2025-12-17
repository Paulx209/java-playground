package com.sonicge.testCompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * // 如果完成则返回结果，否则就抛出具体的异常
 * public T get() throws InterruptedException, ExecutionException
 * <p>
 * // 最大时间等待返回结果，否则就抛出具体异常
 * public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
 * <p>
 * // 完成时返回结果值，否则抛出unchecked异常。为了更好地符合通用函数形式的使用，如果完成此 CompletableFuture所涉及的计算引发异常，则此方法将引发unchecked异常并将底层异常作为其原因
 * public T join()
 * <p>
 * // 如果完成则返回结果值（或抛出任何遇到的异常），否则返回给定的 valueIfAbsent。
 * public T getNow(T valueIfAbsent)
 * <p>
 * // 如果任务没有完成，返回的值设置为给定值
 * public boolean complete(T value)
 * <p>
 * // 如果任务没有完成，就抛出给定异常
 * public boolean completeExceptionally(Throwable ex)
 *
 */
public class TestDemo03 {
    public static void main(String[] args) {
        test06();
    }

    public static void test01() {
        //测试get方法
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            return "haha";
        });
        try {
            String result = cf.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test02() {
        //测试get方法，带最长等待时间
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("111");
                Thread.sleep(1000);
                return "haha";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            String result = cf.get(2000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test03() {
        //测试join方法 遇到异常的话，会抛出异常 ； 如果可以正常执行的话，就返回结果！
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            System.out.println(1 / 0);
            return "success";
        });

        try {
            String join = cf.join();
            System.out.println(join);
        } catch (Exception e) {
            System.out.println("遇到了异常:" + e.getMessage());
        }
    }

    public static void test04() {
        //测试getNow方法，立马获取执行结果，如果没有结果的话，就会返回传入的参数 ; 如果遇到异常的话，就会抛出异常
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");

            System.out.println(1 / 0);
            return "success";
        });

        try {
            Thread.sleep(10);
            String now = cf.getNow("不存在");
            System.out.println(now);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test05() {
        //complete(T value) 如果cf.complete成功的将任务停止的话，返回值为true；如果没有成功将任务停止的话，返回值为false
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            return "success";
        });
        try {
            Thread.sleep(1000);
            boolean test = cf.complete("test");
            System.out.println(test);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test06() {
        //completeExceptionally(Throwable ex) 返回值还是boolean类型的，如果任务在执行完成之前，成功设置了异常的话，返回true；如果没有成功设置异常的话，返回false！
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("111");
                System.out.println(1 / 0);
                Thread.sleep(1000);
                return "success";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            Thread.sleep(2000);
            boolean res = cf.completeExceptionally(new RuntimeException("测试异常"));
            System.out.println(res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

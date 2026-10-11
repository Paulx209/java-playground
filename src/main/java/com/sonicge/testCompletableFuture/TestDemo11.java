package com.sonicge.testCompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-11
 */

public class TestDemo11 {
    public static void main(String[] args) {
        ExecutorService myThreadPool = Executors.newFixedThreadPool(5);
        List<String> nameList = List.of("老大", "老二", "老三", "老四", "老五");
        AtomicInteger index = new AtomicInteger(0);
        List<CompletableFuture<String>> futures = nameList.stream().map(
                name -> {
                    return CompletableFuture.supplyAsync(() -> {
                        try {
                            String executeLog = "第" + index.incrementAndGet() + "个coder:" + name + ",正在通过" + Thread.currentThread().getName() + " 执行业务逻辑...";
                            System.out.println(executeLog);
                            Thread.sleep(2000);
                            return name + "执行完毕";
                        } catch (InterruptedException e) {
                            //恢复中断标记
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }, myThreadPool);
                }).collect(Collectors.toList());
        List<String> list = futures.stream().map(CompletableFuture::join)
                .toList();
        for(String str : list){
            System.out.println(str);
        }
        myThreadPool.shutdown();
    }
}


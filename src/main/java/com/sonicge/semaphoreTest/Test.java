package com.sonicge.semaphoreTest;


import java.util.concurrent.Semaphore;

/**
 *@Author: sonicge
 *@CreateTime: 2026-08-19
 */

public class Test {
    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String threadName = "thread-" + i;
            Thread thread = new Thread(Test::execute, threadName);
            thread.start();
        }
    }

    static void execute() {
        boolean acquired = false;
        try {
            String threadName = Thread.currentThread().getName();
            while (true) {
                acquired = semaphore.tryAcquire();
                if (acquired) {
                    System.out.println(threadName + "进入收费口");
                    Thread.sleep(1500);
                    System.out.println(threadName + "驶离收费口");
                    return;
                }
                System.out.println("[ Error: 当前并发量已达到上限 ]" + threadName);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (acquired) {
                semaphore.release();
            }
        }
    }
}

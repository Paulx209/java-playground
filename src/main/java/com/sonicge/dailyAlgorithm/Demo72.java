package com.sonicge.dailyAlgorithm;


import java.util.concurrent.locks.ReentrantLock;

/**
 *@Author: sonicge
 *@CreateTime: 2026-04-03
 */

public class Demo72 {
    private static ReentrantLock lock = new ReentrantLock();
    private static volatile Integer ticket = 100;

    public static void sailTicket() {
        try {
            lock.lock();
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "正在卖第" + (100 - ticket + 1) + "张票");
                ticket--;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sailTicket();
            }
        }, "sonicge");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sailTicket();
            }
        }, "etge");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                sailTicket();
            }
        }, "geentao");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

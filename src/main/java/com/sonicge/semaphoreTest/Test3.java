package com.sonicge.semaphoreTest;

import java.util.concurrent.Semaphore;

/**
 *@Author: sonicge
 *@CreateTime: 2026-09-30
 */

public class Test3 {
    public static void main(String[] args) {
        //全局变量
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean flag = false;
                    for (int j = 0; j < 3; j++) {
                        try {
                            flag = semaphore.tryAcquire();
                            if (flag) {
                                System.out.println(Thread.currentThread().getName() + " 争抢到了信号量");
                                System.out.println("正在处理业务逻辑....");
                                Thread.sleep(2000);
                                break;
                            } else {
                                Thread.sleep(1000 * (j + 1));
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            //拿到信号量再释放
                            if (flag) {
                                System.out.println(Thread.currentThread().getName() +" 释放了信号量");
                                semaphore.release();
                            }
                        }
                    }
                }
            }, "thread - " + i);
            thread.start();
        }
    }
}

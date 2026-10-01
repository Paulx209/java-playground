package com.sonicge.semaphoreTest;


import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


/**
 *@Author: sonicge
 *@CreateTime: 2026-09-30
 */

public class RedissonSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        // 获取分布式信号量 存在宕机之后 许可无法释放的问题
        RSemaphore semaphore = redisson.getSemaphore("mySemaphore");
        // 赋10个令牌
        semaphore.trySetPermits(10);

        // 获取许可
        semaphore.acquire();
        System.out.println("获取许可，开始上传");


        // 模拟上传
        Thread.sleep(5000);

        // 释放许可 不会检查许可的线程之前是否成功拿到许可
        semaphore.release();
        System.out.println("上传完成，释放许可");

        // 关闭 Redisson 客户端
        redisson.shutdown();

    }
}

package com.sonicge.semaphoreTest;

import org.redisson.Redisson;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * 专门用来解决许可在机器宕机之后无法被释放的问题，给许可添加过期时间
 *@Author: sonicge
 *@CreateTime: 2026-10-01
 */
public class RedissonRPermitExpirableSemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RPermitExpirableSemaphore myExpirableSemaphore = redisson.getPermitExpirableSemaphore("myExpirableSemaphore");
        //初始化信号量许可数量
        myExpirableSemaphore.trySetPermits(10);

        //5s获取时间  获取成功 10s过期；超过5s 获取失败
        String permitId = myExpirableSemaphore.tryAcquire(5, 10, TimeUnit.SECONDS);

        if(permitId != null){
            try {
                System.out.println(Thread.currentThread().getName()+ " 正在执行用户逻辑");
                Thread.sleep(1000);
                System.out.println("执行完毕");
            } finally {
                myExpirableSemaphore.release(permitId);
                System.out.println("许可已释放");
            }
        }else{
            System.out.println("信号量争抢失败...");
        }
    }
}

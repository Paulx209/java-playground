package com.sonicge.sse.sseServer;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-16
 */

@Configuration
public class ThreadPoolConfig {

    @Bean("sseExecutor")
    public ExecutorService sseExecutor() {
        return new ThreadPoolExecutor(
                10,
                50,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadFactory() {
                    private final AtomicInteger threadIndex = new AtomicInteger(0);

                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        Thread t = new Thread(r, "sse-push" + threadIndex.getAndDecrement());
                        t.setDaemon(true);
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}

package com.sonicge.other;

/**
 * 双重检测锁 实现 单例模式
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-27
 */
public class SingletonInstance {
    private volatile static SingletonInstance singletonInstance;

    private SingletonInstance() {
    }

    /**
     * 双重检测锁获取单例模式
     *
     * @return singletonInstance
     */
    public static SingletonInstance getInstance() {
        if (singletonInstance != null) return singletonInstance;
        //这里隐藏了我们的一层校验逻辑
        synchronized (SingletonInstance.class) {
            //第二层校验逻辑
            if (singletonInstance == null) {
                singletonInstance = new SingletonInstance();
            }
        }
        return singletonInstance;
    }
}

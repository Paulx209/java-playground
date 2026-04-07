package com.sonicge.dailyAlgorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 【LRU缓存】
 *@Author: sonicge
 *@CreateTime: 2026-04-07
 */

public class Demo77 {
    private Integer capacity;
    private Map<Integer, Integer> map = new LinkedHashMap<>();

    public Demo77(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取值的时候
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    /**
     * 存储值
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
        //判断是否超出容量
        if (map.size() >= capacity) {
            //删除最后一个
            Integer removedKey = map.keySet().iterator().next();
            map.remove(removedKey);
        }
        map.put(key, value);
    }
}

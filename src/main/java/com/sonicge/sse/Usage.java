package com.sonicge.sse;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-15
 */

public class Usage {
    int promptTokens; //输入token （缓存命中token 缓存创建token 其他token）
    int completionTokens; //输出token
    int totalTokens; //总token

    @Override
    public String toString() {
        return String.format("prompt=%d, completion=%d, total=%d",
                promptTokens, completionTokens, totalTokens);
    }
}

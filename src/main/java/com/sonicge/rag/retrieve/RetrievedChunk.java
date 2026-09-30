package com.sonicge.rag.retrieve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 检索到的chunk块
 * @Author: sonicge
 * @CreateTime: 2026-09-30
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RetrievedChunk {
    private String content; //chunk的内容
    private String source ; //来源文档名
    private String sourceUrl; //原文链接
    private String updateTime; //更新时间
    private Double score; //得分
}



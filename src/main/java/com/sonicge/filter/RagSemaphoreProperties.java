package com.sonicge.filter;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-01
 */

@Data
@Configuration
@ConditionalOnProperty(prefix = "rag.semaphore")
public class RagSemaphoreProperties {


    private String name = "rag:document:upload";

    //最大并发数
    private Integer maxConcurrent = 10;

    //申请许可的最大等待时间
    private Integer maxWaitSeconds =30;

    //释放许可的过期时间
    private Integer releaseSeconds = 60;
}

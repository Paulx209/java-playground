package com.sonicge.filter;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 在filter链上做过滤，避免请求到达MultipartFile层，将请求体中的数据下载到临时文件中。
 * filter过滤器是Servlet的组件，Interceptor拦截器是Spring Mvc的组件，两者执行的时机不同。
 * 1.filter过滤器会在请求到达DispatcherServlet之前执行，比如说文件上传请求，此时临时文件还没有写入磁盘
 * 2.Interceptor拦截器一般会在controller方法执行之前或者执行后执行。
 *@Author: sonicge
 *@CreateTime: 2026-10-01
 */

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class UploadRateLimitFilter extends OncePerRequestFilter{
    private final String UPLOAD_PATH_PATTERN = "/knowledge-base/";
    private final String UPLOAD_PATH_SUFFIX = "/docs/upload";
    private final RedissonClient redissonClient;
    private final RagSemaphoreProperties semaphoreProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        //get请求直接放行 只对特定的路径做处理
        if(!isUploadRequest(request)){
            filterChain.doFilter(request,response);
            return;
        }
        String key = semaphoreProperties.getName();
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore(key);

        String permitId = null;
        try {
            permitId = semaphore.tryAcquire(semaphoreProperties.getMaxWaitSeconds(), semaphoreProperties.getReleaseSeconds(), TimeUnit.SECONDS);
            //许可获取失败 返回
            if(permitId == null){
                response.setStatus(429);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\":\"429\",\"message\":\"当前上传人数过多，请稍后再试\"}");
                return;
            }
            //许可获取成功 放行
            filterChain.doFilter(request, response);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            response.setStatus(500);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":\"500\",\"message\":\"获取上传许可失败\"}");
        } finally {
            //不为null 释放
            if(!permitId.isEmpty()){
                boolean flag = semaphore.tryRelease(permitId);
                if(!flag){
                    log.warn("upload permit already expired or released, permitId={}", permitId);
                }
            }
        }
    }

    /**
     * 判断是否是文档上传请求
     */
    private boolean isUploadRequest(HttpServletRequest request) {
        if (!"POST".equals(request.getMethod())) {
            return false;
        }
        String uri = request.getRequestURI();
        return uri != null && uri.contains(UPLOAD_PATH_PATTERN) && uri.endsWith(UPLOAD_PATH_SUFFIX);
    }

}

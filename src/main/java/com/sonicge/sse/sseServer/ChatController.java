package com.sonicge.sse.sseServer;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-16
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/stream")
    public SseEmitter stream(@RequestParam String question) {
        //创建SseEmitter 超时3分钟
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L * 3);

        //设置回调
        sseEmitter.onCompletion(() -> {
            System.out.println("Sse 连接关闭");
            sseEmitter.complete();
        });
        sseEmitter.onTimeout(() -> {
                    System.out.println("SSE 连接超时");
                    sseEmitter.complete();
                }
        );

        sseEmitter.onError(e -> {
                    System.err.println("SSE 连接异常: " + e.getMessage());
                    sseEmitter.complete();
                }
        );

        chatService.streamChat(question, sseEmitter);
        return sseEmitter;
    }
}

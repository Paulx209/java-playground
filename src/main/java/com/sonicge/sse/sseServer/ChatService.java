package com.sonicge.sse.sseServer;

import com.google.gson.*;
import jakarta.annotation.Resource;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 *@Author: sonicge
 *@CreateTime: 2026-10-16
 */
@Service
public class ChatService {
    private static final String base_url = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String api_key = "";
    private static final String model = "Qwen/Qwen3-32B";
    @Resource(name = "sseExecutor")
    private ExecutorService sseExecutor;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    private final ScheduledExecutorService heartBeatExecutor = Executors.newScheduledThreadPool(2);

    private final Gson gson = new Gson();

    public void streamChat(String question, SseEmitter sseEmitter) {
        //心跳保活 15秒发一次
        startHeartKeepAlive(sseEmitter, 15, 15);
        //流式聊天
        sseExecutor.submit(() -> doStreamChat(question, sseEmitter));
    }

    private void startHeartKeepAlive(SseEmitter sseEmitter, long periodTime, long initDelayTime) {
        heartBeatExecutor.scheduleAtFixedRate(() -> {
            try {
                sseEmitter.send(SseEmitter.event().comment("keepalive"));
            } catch (Exception e) {
                //无需处理
            }
        }, periodTime, initDelayTime, TimeUnit.SECONDS);
    }

    /**
     * 异步调用大模型流式 API，逐 Token 通过 SseEmitter 转发给前端
     */
    private void doStreamChat(String question, SseEmitter sseEmitter) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", model);
        requestBody.addProperty("temperature", 0.7);
        requestBody.addProperty("max_tokens", 2048);
        requestBody.addProperty("stream", true); // stream + text/event-stream   服务端就会按照sse协议来发送数据

        JsonArray messageList = new JsonArray();
        JsonObject systemMsg = new JsonObject();
        systemMsg.addProperty("role", "system");
        systemMsg.addProperty("content", "你是一个技术专家，回答简洁清晰。");
        messageList.add(systemMsg);

        if (question != null && !question.isEmpty()) {
            JsonObject userMsg = new JsonObject();
            userMsg.addProperty("role", "message");
            userMsg.addProperty("content", question);
            messageList.add(userMsg);
        }
        requestBody.add("messages", messageList);


        // 2. 发起Http请求
        Request request = new Request.Builder()
                .url(base_url)
                .addHeader("Authorization", "Bearer " + api_key)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "text/event-stream")
                .post(RequestBody.create(requestBody.toString(),
                        MediaType.parse("application/json")))
                .build();

        // 3. 解析 SSE 流并转发
        try (Response response = httpClient.newCall(request).execute()) {
            // 3.1 响应失败
            if (!response.isSuccessful()) {
                sendError(sseEmitter, response.code(),
                        "大模型 API 调用失败: HTTP " + response.code());
                return;
            }

            if (response.body() == null) {
                sendError(sseEmitter, response.code(), "响应体为空");
                return;
            }

            //3.2 流式读取 readLine() -> 避免tcp的粘包和拆包问题 -> Sse返回的数据本来就是按照行来分割的，天然遵循这种数据格式
            //如果使用getBytes方法一次性获得全部的字节流的话，需要自己做分割处理
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.body().byteStream(),
                            StandardCharsets.UTF_8));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.startsWith(":")) {
                    continue;
                }
                if (!line.startsWith("data:")) {
                    continue;
                }

                String data = line.substring(5);
                if (data.startsWith(" ")) {
                    data = data.substring(1);
                }

                if ("[DONE]".equals(data)) {
                    // 3.3 流结束，发送 done 事件
                    sseEmitter.send(SseEmitter.event()
                            .name("done")
                            .data("{\"msg\":\"completed\"}"));
                    sseEmitter.complete();
                    return;
                }

                // 解析 JSON
                JsonObject chunk;
                try {
                    chunk = JsonParser.parseString(data).getAsJsonObject();
                } catch (Exception e) {
                    continue;  // JSON 解析失败，跳过
                }

                // 提取 content
                JsonArray choices = chunk.getAsJsonArray("choices");
                if (choices == null || choices.isEmpty()) {
                    // 3.4 可能是只有 usage 的 chunk -> 代表完成
                    if (chunk.has("usage") && !chunk.get("usage").isJsonNull()) {
                        JsonObject usage = chunk.getAsJsonObject("usage");
                        sseEmitter.send(SseEmitter.event()
                                .name("done")
                                .data(gson.toJson(usage)));
                        sseEmitter.complete();
                        return;
                    }
                    continue;
                }

                JsonObject choice = choices.get(0).getAsJsonObject();
                JsonObject delta = choice.getAsJsonObject("delta");

                if (delta != null && delta.has("content")) {
                    JsonElement contentElement = delta.get("content");
                    if (!contentElement.isJsonNull()) {
                        String token = contentElement.getAsString();
                        if (!token.isEmpty()) {
                            // 3.5 核心：通过 SseEmitter 把 token 推送给前端
                            sseEmitter.send(SseEmitter.event()
                                    .name("token")
                                    .data("{\"content\":\"" +
                                            escapeJson(token) + "\"}"));
                        }
                    }
                }

                // 检查 finish_reason，提取 usage
                JsonElement finishElement = choice.get("finish_reason");
                if (finishElement != null && !finishElement.isJsonNull()) {
                    if (chunk.has("usage") && !chunk.get("usage").isJsonNull()) {
                        JsonObject usage = chunk.getAsJsonObject("usage");
                        //3.6 完成
                        sseEmitter.send(SseEmitter.event()
                                .name("done")
                                .data(gson.toJson(usage)));
                        sseEmitter.complete();
                        return;
                    }
                }
            }

            // readLine 返回 null 但没收到 [DONE]，连接异常
            sendError(sseEmitter, 500, "流式响应异常结束");

        } catch (IOException e) {
            sendError(sseEmitter, 500, "连接异常: " + e.getMessage());
        }
    }

    /** 向前端推送错误事件 */
    private void sendError(SseEmitter sseEmitter, int code, String message) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .name("error")
                    .data("{\"code\":" + code + ",\"message\":\"" +
                            escapeJson(message) + "\"}"));
            sseEmitter.complete();
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }

    /** 简单的 JSON 字符串转义 */
    private String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}

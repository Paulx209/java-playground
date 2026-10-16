package com.sonicge.sse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 *@Author: sonicge
 *@CreateTime: 2026-10-15
 */

public class SseStreamClient {
    private static final String base_url = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String api_key = "";


    public void streamChat(String model, String systemPrompt, String userMessage, StreamCallBack callback) {
        //1.构建请求体
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", model);
        requestBody.addProperty("temperature", 0.7);
        requestBody.addProperty("max_tokens", 2048);
        requestBody.addProperty("stream", true);

        JsonArray messages = new JsonArray();
        if (systemPrompt != null && !systemPrompt.isEmpty()) {
            JsonObject systemMessage = new JsonObject();
            systemMessage.addProperty("role", "system");
            systemMessage.addProperty("message", systemPrompt);
            messages.add(systemMessage);
        }

        JsonObject userMsg = new JsonObject();
        userMsg.addProperty("role", "user");
        userMsg.addProperty("content", userMessage);
        messages.add(userMsg);
        requestBody.add("messages", messages);

        //2.创建Http客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(base_url)
                .addHeader("Authorization", "Bearer " + api_key)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "text/event-stream")
                .post(RequestBody.create(requestBody.toString(), MediaType.parse("application/json")))
                .build();

        //3. 发起请求并解析SSE流
        StringBuilder fullContent = new StringBuilder();
        Usage usage = null;
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "无响应体";
                callback.onError(new RuntimeException("HTTP " + response.code() + ": " + errorBody)
                        , fullContent.toString());
                return;
            }
            // 逐行读取 SSE 流（显式指定 UTF-8，SSE 规范要求 UTF-8 编码）
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream(), StandardCharsets.UTF_8));
            String line;
            boolean streamDone = false; //是否收到了done
            while ((line = reader.readLine()) != null) {
                //跳过空行 SSE事件分隔符
                if (line.isEmpty()) {
                    continue;
                }
                //注释内容 （心脏监测保活）
                if (line.startsWith(":")) {
                    continue;
                }
                //只处理data:开头的数据行{
                if (!line.startsWith("data:")) {
                    continue;
                }

                // 去掉 "data:" 前缀，SSE 标准规定冒号后最多去掉一个可选空格
                String data = line.substring(5);
                if (data.startsWith(" ")) {
                    data = data.substring(1);
                }

                // 检查流结束标记
                if ("[DONE]".equals(data)) {
                    streamDone = true;
                    break;
                }

                // 解析 JSON（加容错）
                JsonObject chunk;
                try {
                    chunk = JsonParser.parseString(data).getAsJsonObject();
                } catch (Exception e) {
                    // JSON 解析失败，跳过这个 chunk，不要中断整个流
                    System.err.println("JSON 解析失败，跳过: " + data);
                    continue;
                }

                JsonArray choices = chunk.getAsJsonArray("choices");
                if (choices == null || choices.isEmpty()) {
                    // 有些平台在最后一个 chunk（stream_options 模式）choices 为空数组
                    // 但可能有 usage 字段
                    usage = extractUsage(chunk, usage);
                    continue;
                }

                //这里只取下标为0的是因为，下标为1的是候选答案；本次event输出的数据都在data:里面，数据多的话，会通过多行data:来表示
                JsonObject choice = choices.get(0).getAsJsonObject();
                //提取delta中的content
                JsonObject delta = choice.getAsJsonObject("delta");
                if (delta != null && delta.has("content")) {
                    JsonElement content = delta.get("content");
                    if (!content.isJsonNull()) {
                        String token = content.getAsString();
                        if (!token.isEmpty()) {
                            fullContent.append(token);
                            callback.onToken(token);
                        }
                    }
                }
                JsonElement finishReason = choice.get("finish_reason");
                if (finishReason != null && !finishReason.isJsonNull()) {
                    String stop_reason = finishReason.getAsString();
                    usage = extractUsage(chunk, usage);
                }
            }
            if (streamDone) {
                callback.onComplete(fullContent.toString(), usage);
            } else {
                callback.onError(new RuntimeException("SSE 流异常结束：未收到 [DONE] 标记"),
                        fullContent.toString());
            }

        } catch (Exception e) {
            // 连接异常（超时、网络中断等），把已接收到的内容传给调用方
            callback.onError(e, fullContent.toString());
        }
    }

    /**
     * 从 chunk 中提取 usage 信息
     */
    private static Usage extractUsage(JsonObject chunk, Usage existing) {
        if (!chunk.has("usage") || chunk.get("usage").isJsonNull()) {
            return existing;
        }
        JsonObject usageJson = chunk.getAsJsonObject("usage");
        Usage usage = new Usage();
        usage.promptTokens = usageJson.has("prompt_tokens")
                ? usageJson.get("prompt_tokens").getAsInt() : 0;
        usage.completionTokens = usageJson.has("completion_tokens")
                ? usageJson.get("completion_tokens").getAsInt() : 0;
        usage.totalTokens = usageJson.has("total_tokens")
                ? usageJson.get("total_tokens").getAsInt() : 0;
        return usage;
    }

    public static void main(String[] args) {
        System.out.println("=== SSE 流式调用演示 ===\n");
        SseStreamClient client = new SseStreamClient();
        client.streamChat(
                "Qwen/Qwen3-32B",
                "你是一个技术专家，回答简洁清晰。",
                "用两三句话解释一下什么是 SSE 协议？",
                new StreamCallBack() {
                    @Override
                    public void onToken(String token) {
                        // 每收到一个 token 就实时输出（不换行）
                        System.out.print(token);
                    }

                    @Override
                    public void onComplete(String fullContent, Usage usage) {
                        System.out.println("\n");
                        System.out.println("--- 流式输出完毕 ---");
                        System.out.println("完整内容长度：" + fullContent.length() + " 字符");
                        if (usage != null) {
                            System.out.println("Token 统计：" + usage);
                        } else {
                            System.out.println("Token 统计：未返回");
                        }
                    }

                    @Override
                    public void onError(Exception e, String partialContent) {
                        System.err.println("\n\n--- 发生错误 ---");
                        System.err.println("错误信息：" + e.getMessage());
                        if (!partialContent.isEmpty()) {
                            System.err.println("已接收到的内容：" + partialContent);
                        }
                    }
                }
        );
    }
}

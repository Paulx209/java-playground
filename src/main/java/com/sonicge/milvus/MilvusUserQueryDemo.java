package com.sonicge.milvus;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.request.data.BaseVector;
import io.milvus.v2.service.vector.response.SearchResp;
import okhttp3.*;
import io.milvus.v2.service.vector.request.data.FloatVec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 *@Author: sonicge
 *@CreateTime: 2026-09-29
 */

public class MilvusUserQueryDemo {
    private static final String SILICONFLOW_API_KEY = "";
    private static final String EMBEDDING_URL = "https://api.siliconflow.cn/v1/embeddings";
    private static final String EMBEDDING_MODEL = "Qwen/Qwen3-Embedding-8B";
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)   // 建连 30s
            .readTimeout(60, TimeUnit.SECONDS)      // 读响应 60s
            .writeTimeout(60, TimeUnit.SECONDS)     // 写请求 60s
            .callTimeout(120, TimeUnit.SECONDS)     // 整个调用最多 120s（兜底）
            .build();
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        MilvusClientV2 client = createClient();
        Scanner sc = new Scanner(System.in);
        System.out.println("亲爱的用户您好，请输入你在使用中遇到的问题：");
        String userQuery = sc.next();
        try {
            //1. user query text -> user query vector
            List<List<Float>> queryVectors = getEmbeddings(List.of(userQuery));
            //2. List<Float> -> BaseVector
            List<BaseVector> milvusQueryVectors = queryVectors.stream()
                    .map(FloatVec::new)   // FloatVec(List<Float>)
                    .collect(java.util.stream.Collectors.toList());

            //3.向量检索请求
            SearchReq searchReq = SearchReq.builder()
                    .collectionName("customer_service_chunks")
                    .data(milvusQueryVectors)           // user query vector
                    .topK(3)                      // 返回最相似的 3 个结果
                    .outputFields(List.of("chunk_text", "doc_id", "category"))  // 需要返回的字段
                    .annsField("vector")          // 指定在哪个向量字段上检索
                    .searchParams(Map.of("ef", 128))  // HNSW 检索时的搜索宽度
                    .filter("category == \"return_policy\"")
                    .build();

            SearchResp search = client.search(searchReq);
            parseResp(search);
        } catch (IOException e) {
            System.out.println("词嵌入过程中遇到问题:" + e.getMessage());
        }
    }
    private static void parseResp(SearchResp search){
        List<List<SearchResp.SearchResult>> searchResults = search.getSearchResults();
        for (List<SearchResp.SearchResult> resultList : searchResults) {
            System.out.println("=== 检索结果 ===");
            for (int i = 0; i < resultList.size(); i++) {
                SearchResp.SearchResult result = resultList.get(i);
                System.out.println("Top-" + (i + 1) + "：");
                System.out.println("  相似度分数：" + result.getScore());
                System.out.println("  分类：" + result.getEntity().get("category"));
                System.out.println("  文档ID：" + result.getEntity().get("doc_id"));
                System.out.println("  内容：" + result.getEntity().get("chunk_text"));
                System.out.println();
            }
        }
    }


    private static List<List<Float>> getEmbeddings(List<String> texts) throws IOException {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", EMBEDDING_MODEL);
        requestBody.add("input", GSON.toJsonTree(texts));

        Request request = new Request.Builder()
                .url(EMBEDDING_URL)
                .addHeader("Authorization", "Bearer " + SILICONFLOW_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(GSON.toJson(requestBody),
                        MediaType.parse("application/json")))
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            String body = response.body().string();
            JsonObject json = GSON.fromJson(body, JsonObject.class);
            JsonArray dataArray = json.getAsJsonArray("data");

            List<List<Float>> vectors = new ArrayList<>();
            for (int i = 0; i < dataArray.size(); i++) {
                JsonArray embeddingArray = dataArray.get(i).getAsJsonObject()
                        .getAsJsonArray("embedding");
                List<Float> vector = new ArrayList<>();
                for (int j = 0; j < embeddingArray.size(); j++) {
                    vector.add(embeddingArray.get(j).getAsFloat());
                }
                vectors.add(vector);
            }
            return vectors;
        }
    }

    /**
     * 创建操作milvus的client客户端
     */
    private static MilvusClientV2 createClient() {
        return new MilvusClientV2(ConnectConfig.builder()
                .uri("http://localhost:19530")
                .build());
    }

}

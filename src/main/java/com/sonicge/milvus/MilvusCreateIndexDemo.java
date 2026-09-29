package com.sonicge.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.IndexParam;
import io.milvus.v2.service.collection.request.LoadCollectionReq;
import io.milvus.v2.service.index.request.CreateIndexReq;

import java.util.List;
import java.util.Map;

/**
 * 【索引的创建】
 *  1. 索引创建不是一次性计算全部向量的随机高度，而是按照先后的顺序，因此第一个向量在插入的时候，不论在哪个layer，都不会有其他的向量，因此不会创建边
 *  2. 后续向量在插入的时候，创建邻居边的话，会创建一个双向边，向量a -> 向量b 向量b -> 向量a;
 *  3. 但是双向边也有可能退化成单向边，因为每一个向量的边数通过 m 参数来控制，当上限之后，会进行择优淘汰，b在a这里排第1，a在b那里排第100。
 *
 *@Author: sonicge
 *@CreateTime: 2026-09-29
 */

public class MilvusCreateIndexDemo {
    public static void main(String[] args) {
        MilvusClientV2 client = createClient();
        LoadCollectionReq loadCollectionReq = loadCollectionHelp();
        client.loadCollection(loadCollectionReq);
        System.out.println("Collection 已加载到内存");
    }

    private static LoadCollectionReq loadCollectionHelp(){
        return LoadCollectionReq.builder()
                .collectionName("customer_service_chunks")
                .build();
    }

    private static MilvusClientV2 createClient() {
        return new MilvusClientV2(ConnectConfig.builder()
                .uri("http://localhost:19530")
                .build());
    }

    private static CreateIndexReq createIndexHelp() {
        IndexParam vectorIndex = IndexParam.builder()
                .fieldName("vector")
                .indexType(IndexParam.IndexType.HNSW)
                .metricType(IndexParam.MetricType.COSINE)// 余弦相似度
                .extraParams(Map.of(
                        "M", 16,              // 每个向量的最大连接数
                        "efConstruction", 256 // 建索引时的搜索宽度
                ))
                .build();

        IndexParam categoryIndex = IndexParam.builder()
                .fieldName("category")
                .indexType(IndexParam.IndexType.TRIE)
                .build();

        return CreateIndexReq.builder()
                .collectionName("customer_service_chunks")
                .indexParams(List.of(vectorIndex, categoryIndex))
                .build();

    }
}

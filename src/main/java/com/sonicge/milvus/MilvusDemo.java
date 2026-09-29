package com.sonicge.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;

/**
 *@Author: sonicge
 *@CreateTime: 2026-09-29
 */

public class MilvusDemo {
    //向量维数
    private static final int VECTOR_DIM = 4096;
    //collection_name
    private static final String COLLECTION_NAME = "customer_service_chunks";

    public static void main(String[] args) {
        ConnectConfig connectConfig = ConnectConfig.builder()
                .uri("http://localhost:19530")
                .build();
        MilvusClientV2 client = null;
        try {
            client = new MilvusClientV2(connectConfig);
        } catch (Exception e) {
            System.out.println("连接异常:" + e.getMessage());
        }

        System.out.println("成功连接到milvus");

        try {
            //1.创建Collection
            CreateCollectionReq.CollectionSchema schema = client.createSchema();

            // 主键字段：自增 ID
            schema.addField(AddFieldReq.builder()
                    .fieldName("id")
                    .dataType(DataType.Int64)
                    .isPrimaryKey(true)
                    .autoID(true)
                    .build());

            // 向量字段：存储 Embedding 向量
            schema.addField(AddFieldReq.builder()
                    .fieldName("vector")
                    .dataType(DataType.FloatVector)
                    .dimension(VECTOR_DIM)
                    .build());

            // 标量字段：chunk 原文
            schema.addField(AddFieldReq.builder()
                    .fieldName("chunk_text")
                    .dataType(DataType.VarChar)
                    .maxLength(8192)
                    .build());

            // 标量字段：文档 ID（标识这个 chunk 来自哪个文档）
            schema.addField(AddFieldReq.builder()
                    .fieldName("doc_id")
                    .dataType(DataType.VarChar)
                    .maxLength(64)
                    .build());

            // 标量字段：分类（退货政策、物流规则、促销活动等）
            schema.addField(AddFieldReq.builder()
                    .fieldName("category")
                    .dataType(DataType.VarChar)
                    .maxLength(32)
                    .build());

            client.createCollection(CreateCollectionReq.builder()
                    .collectionName(COLLECTION_NAME)
                    .collectionSchema(schema)
                    .build());
        } catch (Exception e) {
            System.out.println("Collection创建失败，报错原因为：" + e.getMessage());
        }
        System.out.println("Collection:创建成功");
    }
}

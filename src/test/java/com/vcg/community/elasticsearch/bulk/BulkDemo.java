package com.vcg.community.elasticsearch.bulk;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import java.io.IOException;
import java.util.Date;

/**
 * @author lei.fang
 * @since 2017/4/6
 */
public class BulkDemo extends BaseDemo {

    @Test
    public void test() throws IOException {

        BulkRequestBuilder bulkRequestBuilder = new BulkRequestBuilder(client);
        bulkRequestBuilder.add(
                client.prepareIndex("twitter", "tweet", "1")
                        .setSource(XContentFactory.jsonBuilder()
                                .startObject()
                                .field("user", "kimchy")
                                .field("postDate", new Date())
                                .field("message", "trying out Elasticsearch")
                                .endObject()
                        )
        );

        bulkRequestBuilder.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();

        System.out.println(JSON.toJSONString(bulkResponse));

    }

}

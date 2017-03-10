package com.vcg.community.elasticsearch.elasticsearch.index;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.util.Date;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/bulk.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class BulkDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
        );

        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()
                )
        );

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();

    }
}

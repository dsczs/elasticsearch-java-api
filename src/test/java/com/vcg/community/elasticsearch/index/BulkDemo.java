package com.vcg.community.elasticsearch.index;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.mapping.Tweet;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/bulk.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class BulkDemo extends BaseDemo {

    @Test
    public void testForClient() throws Exception {
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

    @Test
    public void testForElasticsearchTemplate() throws Exception{

        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setUser("kimchy");
        tweet.setPostDate(new Date());
        tweet.setMessage("trying out Elasticsearch");
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("1");
        indexQuery.setIndexName("twitter");
        indexQuery.setType("tweet");
        indexQuery.setSource(JSON.toJSONString(tweet));

        Tweet tweet1 = new Tweet();
        tweet1.setId("1");
        tweet1.setUser("kimchy");
        tweet1.setPostDate(new Date());
        tweet1.setMessage("trying out Elasticsearch");
        IndexQuery indexQuery1 = new IndexQuery();
        indexQuery1.setId("2");
        indexQuery1.setIndexName("twitter");
        indexQuery1.setType("tweet");
        indexQuery1.setSource(JSON.toJSONString(tweet));

        List<IndexQuery> indexQueryList  =new ArrayList<>();
        indexQueryList.add(indexQuery);
        indexQueryList.add(indexQuery1);

        elasticsearchTemplate.bulkIndex(indexQueryList);

    }

}

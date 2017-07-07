package com.vcg.community.elasticsearch.index;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.mapping.Tweet;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href=''https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/index_.html></a>
 * @auhthor felayman@gmail.com
 * @since . 2017-07-07
 */
public class IndexDemo extends BaseDemo {

    /**
     * 使用字符串方式来构建文档内容
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/index-doc.html'></a>
     * @throws Exception
     */
    @Test
    public void testIndexForClientUseStr() throws Exception {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .execute()
                .actionGet();
    }

    /**
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/index-doc.html'></a>
     * 使用map来构建文档内容
     * @throws Exception
     */
    @Test
    public void testIndexForClientUseMap() throws Exception {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(json)
                .execute()
                .actionGet();
    }

    /**
     * 使用Elasticsearch提供的内置json构造器来构建文档内容
     * @throws Exception
     */
    @Test
    public void testIndexForClientUseXContentBuilder() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("message", "trying out Elasticsearch")
                .endObject();
        IndexResponse response = client.prepareIndex("twitter", "tweet")
                .setSource(builder.toString())
                .execute()
                .actionGet();
    }

    /**
     * 使用spring-elasticsearchTemplate 来帮助简化对文档的操作
     * @throws Exception
     */
    @Test
    public void testIndexForElasticsearchTemplate() throws Exception {
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
        elasticsearchTemplate.index(indexQuery);
    }

}

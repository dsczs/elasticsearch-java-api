package com.vcg.community.elasticsearch.get;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/get.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class GetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                .execute()
                .actionGet();
    }
}

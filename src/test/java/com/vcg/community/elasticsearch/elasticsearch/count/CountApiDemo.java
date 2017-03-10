package com.vcg.community.elasticsearch.elasticsearch.count;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

/**
 * @author lei.fang
 * @since 2017/3/8
 */
public class CountApiDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        CountResponse response = client.prepareCount("index")
                .setQuery(QueryBuilders.termQuery("title", "美女"))
                .execute()
                .actionGet();

        System.out.println("count:"+response.getCount());

    }
}

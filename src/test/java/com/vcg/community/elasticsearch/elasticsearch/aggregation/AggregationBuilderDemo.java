package com.vcg.community.elasticsearch.elasticsearch.aggregation;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.Test;

/**
 * @see  <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_bucket_aggregations.html</a>
 * 比较重要
 * @author lei.fang
 * @since 2017/3/8
 */
public class AggregationBuilderDemo  extends BaseDemo{

    @Test
    public void test() throws Exception {
        AggregationBuilder aggregationBuilder = AggregationBuilders.filters("agg")
                .filter("图片", FilterBuilders.termFilter("resourceType","0"))
                .filter("影集", FilterBuilders.termFilter("resourceType","1"))
                .filter("图文", FilterBuilders.termFilter("resourceType","3"));


        SearchResponse searchResponse  = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(aggregationBuilder)
                .execute()
                .actionGet();

        System.out.println(JSON.toJSONString(searchResponse));

    }
}

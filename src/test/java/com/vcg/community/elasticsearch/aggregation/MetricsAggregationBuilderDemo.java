package com.vcg.community.elasticsearch.aggregation;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MetricsAggregationBuilder;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/java-aggs.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class MetricsAggregationBuilderDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        MetricsAggregationBuilder metricsAggregationBuilder = AggregationBuilders.max("rating_agg").field("rating");

        SearchResponse searchResponse  = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(metricsAggregationBuilder)
                .execute()
                .actionGet();

        System.out.println(JSON.toJSONString(searchResponse));

    }
}

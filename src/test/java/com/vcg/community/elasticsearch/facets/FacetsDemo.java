package com.vcg.community.elasticsearch.facets;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.facet.FacetBuilders;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/java-facets.html</a>
 * 切面统计，这里我统计热度值在某个范围内的值
 * @author lei.fang
 * @since 2017/3/8
 */
public class FacetsDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        SearchResponse searchResponse = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addFacet(
                        FacetBuilders
                        .rangeFacet("f_rating")
                        .addRange(70,80)
                        .field("rating")
                )
                .execute().actionGet();

        System.out.println(JSON.toJSONString(searchResponse.getHits()));

    }
}

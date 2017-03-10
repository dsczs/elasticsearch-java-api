package com.vcg.community.elasticsearch.elasticsearch.facets;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacet;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * 统计某个时间字段
 * @author lei.fang
 * @since 2017/3/10
 */
public class DateHistogramFacetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        SearchResponse searchResponse = client.prepareSearch("index")
                .addFacet(
                        FacetBuilders.dateHistogramFacet("f")
                        .field("createdDate")      // Your date field
                        .interval("year"))
                .execute()
                .actionGet();

        DateHistogramFacet f = (DateHistogramFacet) searchResponse.getFacets().facetsAsMap().get("f");

        for (DateHistogramFacet.Entry entry : f) {
            entry.getTime();    // Date in ms since epoch (X-Axis)
            entry.getCount();   // Doc count (Y-Axis)
        }

    }
}

package com.vcg.community.elasticsearch.facets;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.range.RangeFacet;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * @author lei.fang
 * @since 2017/3/10
 */
public class RangeFacetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        SearchResponse searchResponse = client.prepareSearch()
                .addFacet(
                        FacetBuilders.rangeFacet("f")
                                .field("rating")  //需要统计的字段
                                .addUnboundedFrom(20) //不包含边界
                                .addRange(20,100) //从20-100之间，不包含100
                                .addUnboundedTo(100)
                )
                .execute()
                .actionGet();

        RangeFacet rangeFacet = (RangeFacet) searchResponse.getFacets().facetsAsMap().get("f");
        for (RangeFacet.Entry entry : rangeFacet) {
            entry.getFrom();    // Range from requested
            entry.getTo();      // Range to requested
            entry.getCount();   // Doc count
            entry.getMin();     // Min value
            entry.getMax();     // Max value
            entry.getMean();    // Mean avg
            entry.getTotal();   // Sum of values
        }
    }
}

package com.vcg.community.elasticsearch.elasticsearch.facets;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.statistical.StatisticalFacet;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * @author lei.fang
 * @since 2017/3/10
 */
public class StatisticalDemo extends BaseDemo {
    @Test
    public void test() throws Exception {

        SearchResponse searchResponse = client.prepareSearch()
                .addFacet(
                        FacetBuilders.statisticalFacet("f")
                                .field("rating")
                )
                .execute()
                .actionGet();

        StatisticalFacet f = (StatisticalFacet) searchResponse.getFacets().facetsAsMap().get("f");

        System.out.println(f.getCount());
        System.out.println(f.getMax());
        System.out.println(f.getMean());
        System.out.println(f.getMin());
        System.out.println(f.getStdDeviation());
        System.out.println(f.getSumOfSquares());
        System.out.println(f.getTotal());
        System.out.println(f.getVariance());

    }
}

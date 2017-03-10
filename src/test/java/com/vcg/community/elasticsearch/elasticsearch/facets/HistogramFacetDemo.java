package com.vcg.community.elasticsearch.elasticsearch.facets;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.histogram.HistogramFacet;
import org.elasticsearch.search.facet.histogram.HistogramFacetBuilder;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * @author lei.fang
 * @since 2017/3/10
 */
public class HistogramFacetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        HistogramFacetBuilder facet = FacetBuilders.histogramFacet("f")
                .field("rating")
                .interval(10);

        SearchResponse searchResponse = client.prepareSearch("index")
                .addFacet(facet)
                .execute()
                .actionGet();

        HistogramFacet f = (HistogramFacet) searchResponse.getFacets().facetsAsMap().get("f");

// For each entry
        for (HistogramFacet.Entry entry : f) {
            entry.getKey();     // Key (X-Axis)
            entry.getCount();   // Doc count (Y-Axis)
        }
    }
}

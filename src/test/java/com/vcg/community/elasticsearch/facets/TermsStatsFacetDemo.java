package com.vcg.community.elasticsearch.facets;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.termsstats.TermsStatsFacet;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * @author lei.fang
 * @since 2017/3/10
 */
public class TermsStatsFacetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        SearchResponse searchResponse = client.prepareSearch()
                .addFacet(
                        FacetBuilders.termsStatsFacet("f")
                                .keyField("categoryId")
                                .valueField("rating")
                )
                .execute()
                .actionGet();

        TermsStatsFacet f = (TermsStatsFacet) searchResponse.getFacets().facetsAsMap().get("f");
        for (TermsStatsFacet.Entry entry : f) {
           System.out.println( entry.getTerm());          // Term
            System.out.println(entry.getCount());           // Doc count
            System.out.println(entry.getMin());             // Min value
            System.out.println(entry.getMax());             // Max value
            System.out.println(entry.getMean());            // Mean
            System.out.println(entry.getTotal());
            System.out.println("------------------------------");// Sum of values
        }
    }
}

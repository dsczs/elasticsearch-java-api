package com.vcg.community.elasticsearch.facets;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;
import org.junit.Test;

/**
 * @see  <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * Facets are deprecated and will be removed in a future release. Please use aggregations instead
 * 未来版本已经不建议使用Facets，将会被移除，请使用aggregations来替代完成Facets的功能
 * @author lei.fang
 * @since 2017/3/8
 */
public class TermFacetsDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        TermsFacetBuilder termsFacetBuilder = FacetBuilders.termsFacet("f").field("categoryId").size(100);

        SearchResponse searchResponse = client.prepareSearch()
                .addFacet(termsFacetBuilder)
                .execute()
                .actionGet();

        TermsFacet termsFacet = (TermsFacet) searchResponse.getFacets().facetsAsMap().get("f");
        System.out.println(termsFacet.getTotalCount()); //Total terms doc count
        System.out.println(termsFacet.getOtherCount()); //Not shown terms doc count
        System.out.println(termsFacet.getMissingCount()); //Without term doc count
    }
}

package com.vcg.community.elasticsearch.elasticsearch.facets;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.query.QueryFacet;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/_facets.html#java-facet-terms</a>
 * @author lei.fang
 * @since 2017/3/10
 */
public class QueryFacetDemo extends BaseDemo {
    @Test
    public void test() throws Exception {

        SearchResponse searchResponse = client.prepareSearch("index")
                .addFacet(
                        FacetBuilders.queryFacet("f",
                                QueryBuilders.matchQuery("categoryId", "0"))
                )
                .execute()
                .actionGet();

        QueryFacet  f = (QueryFacet) searchResponse.getFacets().facetsAsMap().get("f");

        System.out.println(f.getCount());

    }
}

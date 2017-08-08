package org.visualchina.elasticsearch.api.demo.query.depth;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-06
 */
public class MatchQueryDemo extends XPackBaseDemo {


    @Test
    public void testMatchSpecifiedField() throws Exception {



        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title","美女");

        matchQueryBuilder.fuzziness(Fuzziness.AUTO);


        SearchResponse searchResponse = client.prepareSearch()
                .setIndices("2017-08-03-index")
                .setTypes("resource")
                .setQuery(matchQueryBuilder)
                .execute()
                .actionGet();
        println(searchResponse);
    }
}

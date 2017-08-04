package org.visualchina.elasticsearch.api.demo.geo;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;

/**
 * 地理坐标点
 * @see <a href='https://www.elastic.co/guide/cn/elasticsearch/guide/current/lat-lon-formats.html'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
public class GeoPointDemo extends XPackBaseDemo{

    @Before
    public void init(){
    }

    @After
    public void clean(){

    }

    @Test
    public void testGeoBoundingBoxQuery() throws Exception {

        QueryBuilder queryBuilder =
                QueryBuilders.geoBoundingBoxQuery("location")
                        .setCorners(40.124125,113.493763,39.816239,117.237612);



       SearchResponse searchResponse = client.prepareSearch()
                .setIndices("attractions")
                .setTypes("restaurant")
               .setPostFilter(queryBuilder)
                .execute()
                .actionGet();

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit :  searchHits){
            System.out.println(searchHit.getSource());
        }
    }
}

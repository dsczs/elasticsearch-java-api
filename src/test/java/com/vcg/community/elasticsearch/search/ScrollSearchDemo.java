package com.vcg.community.elasticsearch.search;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/scrolling.html#scrolling</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class ScrollSearchDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        SearchResponse searchResponse = client.prepareSearch("index")
                .setSearchType(SearchType.SCAN)
                .setScroll(new TimeValue(60000))
                .setQuery(QueryBuilders.termQuery("title","美女"))
                .setSize(100) //每次滚动取100条数据
                .execute()
                .actionGet();

        int count = 0;

        while (true) {
            for (SearchHit hit : searchResponse.getHits().getHits()) {
                count++;
            }
            searchResponse = client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
            if (searchResponse.getHits().getHits().length == 0) {
                break;
            }
        }

        System.out.println("count:"+count);

    }
}

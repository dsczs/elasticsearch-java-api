package com.vcg.community.elasticsearch.filter;

import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.mapping.Photo;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/ids-filter.html'></a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class IdsFilterDemo extends BaseDemo {

    /**
     *关于ids过滤的java-api
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/ids-filter.html'></a>
     * 关于ids过滤的文档说明
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/query-dsl-filters.html'></a>
     * @throws Exception
     */
    @Test
    public void testForClient() throws Exception {
        FilterBuilder filter = FilterBuilders.idsFilter()
                .addIds("1", "4", "100");
        client.prepareSearch("twitter")
                .setTypes("tweet")
                .setPostFilter(filter)
                .execute()
                .actionGet();
    }

    @Test
    public void testForElasticsearchTemplate(){
        FilterBuilder filterBuilder = FilterBuilders.idsFilter()
                .addIds("0f52c4622918441dad5777507e513fe6")
                .addIds("10a94171e9614f76952cd10bccb2bee3");
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);
        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

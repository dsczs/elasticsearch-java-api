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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/exists-filter.html</a>
 * 查看存在geoCoordinates字段存在
 * @author lei.fang
 * @since 2017/3/8
 */
public class ExistFilterDemo extends BaseDemo {

    /**
     * exists过滤的java-api
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/exists-filter.html'></a>
     * exists过滤的文档说明
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/reference/1.5/query-dsl-exists-filter.html'></a>
     */
    @Test
    public void testForClient(){
        FilterBuilder filter = FilterBuilders.existsFilter("user");
        client.prepareSearch("twitter")
                .setTypes("tweet")
                .setPostFilter(filter)
                .execute()
                .actionGet();
    }

    @Test
    public void testForElasticsearchTemplate(){
        FilterBuilder filterBuilder = FilterBuilders.existsFilter("geoCoordinates");
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);
        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

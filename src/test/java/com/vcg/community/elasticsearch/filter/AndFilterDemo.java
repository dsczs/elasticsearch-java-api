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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/and-filter.html</a>
 * 同时执行rangeFilter + prefixFilter
 * @author lei.fang
 * @since 2017/3/8
 */
public class AndFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.andFilter(
                FilterBuilders
                        .rangeFilter("state")
                        .from("-1")
                        .to("10"),
                FilterBuilders.prefixFilter("title","美女")
        );

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);

    }



}

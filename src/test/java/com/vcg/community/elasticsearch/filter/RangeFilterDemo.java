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
 * @author lei.fang
 * @since 2017/3/8
 */
public class RangeFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.rangeFilter("rating")
                .from(40.0d)
                .to(100.0d)
                .includeUpper(false)
                .includeLower(true);
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }
}

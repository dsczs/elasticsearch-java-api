package com.vcg.community.elasticsearch.elasticsearch.filter;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import com.vcg.community.elasticsearch.model.px500.Photo;
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
public class IdsFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.idsFilter()
                .addIds("0f52c4622918441dad5777507e513fe6")
                .addIds("10a94171e9614f76952cd10bccb2bee3");

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }

}

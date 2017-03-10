package com.vcg.community.elasticsearch.elasticsearch.query;

import com.vcg.community.elasticsearch.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/22
 */
public class TestDemo extends ElasticDemo {

    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("title","苹果"))
                .should(QueryBuilders.termQuery("title","红色"));


        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,2);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }

}

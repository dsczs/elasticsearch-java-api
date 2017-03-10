package com.vcg.community.elasticsearch.elasticsearch.query;

import com.vcg.community.elasticsearch.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class FieldQueryDemo extends ElasticDemo {

    @Test
    public void shouldReturnSpecifiedFields(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withIndices("index_v1")
                .withTypes("resource")
                .withFields("id","title")
                .build();
        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

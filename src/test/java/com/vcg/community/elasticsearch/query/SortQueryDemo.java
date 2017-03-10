package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class SortQueryDemo extends ElasticDemo {

    @Test
    public void testSort(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
               .withQuery(QueryBuilders.matchAllQuery())
                .withSort(new FieldSortBuilder("rating").ignoreUnmapped(true).order(SortOrder.DESC))
                .build();
        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

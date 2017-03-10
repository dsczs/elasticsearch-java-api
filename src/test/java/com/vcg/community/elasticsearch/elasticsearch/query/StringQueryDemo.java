package com.vcg.community.elasticsearch.elasticsearch.query;

import com.vcg.community.elasticsearch.model.px500.Photo;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.StringQuery;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class StringQueryDemo extends ElasticDemo {

    @Test
    public void testStringQuery(){
        StringQuery stringQuery = new StringQuery(matchAllQuery().toString());
        List<Photo> photoList = elasticsearchTemplate.queryForList(stringQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

    @Test
    public void testStringQueryAndPage(){
        StringQuery stringQuery = new StringQuery(matchAllQuery().toString(),new PageRequest(0,10));
        List<Photo> photoList = elasticsearchTemplate.queryForList(stringQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

    @Test
    public void testStringQueryAndPageAndSort(){
        StringQuery stringQuery = new StringQuery(matchAllQuery().toString(),new PageRequest(0,10), new Sort(new Sort.Order(Sort.Direction.ASC,"rating")));
        List<Photo> photoList = elasticsearchTemplate.queryForList(stringQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

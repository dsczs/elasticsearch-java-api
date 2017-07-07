package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class MatchAllQueryDemo  extends ElasticDemo {


    @Test
   public void testGivenSearchQuery(){
       SearchQuery searchQuery =  new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
       long count = elasticsearchTemplate.count(searchQuery, Photo.class);
        Page<Photo> page = elasticsearchTemplate.queryForPage(searchQuery,Photo.class);
        System.out.println("total:"+page.getTotalElements());
        System.out.println("totalPage:"+page.getTotalPages());
        System.out.println("size:"+page.getSize());
        System.out.println("current number:"+page.getNumber());
//        System.out.println("photos:"+ JSON.toJSONString(page.getContent()));
   }


}

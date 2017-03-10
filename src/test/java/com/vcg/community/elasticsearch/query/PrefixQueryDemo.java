package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/prefix.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class PrefixQueryDemo extends ElasticDemo {

    @Test
    public void test(){

        QueryBuilder qb = QueryBuilders.prefixQuery("title","test");

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,4);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);
    }


}

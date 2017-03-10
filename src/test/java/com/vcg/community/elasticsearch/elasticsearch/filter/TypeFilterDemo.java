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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/type-filter.html</a>
 *  限制类型
 * @author lei.fang
 * @since 2017/3/8
 */
public class TypeFilterDemo extends BaseDemo{

    @Test
    public void test(){

        FilterBuilder filterBuilder = FilterBuilders.typeFilter("user");

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }

}

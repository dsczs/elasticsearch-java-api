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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/match-all-filter.html</a>
 * 匹配所有的过滤器,该过滤器存在意义很大，就是当你需要取所有数据的时候不涉及到评分,其实内部会转换成查询matchall
 * @author lei.fang
 * @since 2017/3/8
 */
public class MatchAllFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.matchAllFilter();

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }
}

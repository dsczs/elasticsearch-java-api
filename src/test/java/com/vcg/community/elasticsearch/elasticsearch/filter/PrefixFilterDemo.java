package com.vcg.community.elasticsearch.elasticsearch.filter;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import com.vcg.community.elasticsearch.model.px500.UserMapping;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/prefix-filter.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class PrefixFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.prefixFilter(
                "nickName",
                "felay"
        );
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<UserMapping> photoList = elasticsearchTemplate.queryForList(searchQuery, UserMapping.class);

        System.out.println("photoList:"+photoList);
    }
}

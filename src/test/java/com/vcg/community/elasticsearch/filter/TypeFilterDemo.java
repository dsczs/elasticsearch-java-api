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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/type-filter.html</a>
 *  限制类型
 * @author lei.fang
 * @since 2017/3/8
 */
public class TypeFilterDemo extends BaseDemo {


    /**
     *关于type过滤的java-api
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/type-filter.html'></a>
     * 关于type过滤的文档说明
     * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/query-dsl-filters.html'></a>
     * @throws Exception
     */
    @Test
    public void testForClient() throws Exception {
        FilterBuilder filter = FilterBuilders.typeFilter("tweet");
        client.prepareSearch("twitter")
                .setTypes("tweet")
                .setPostFilter(filter)
                .execute()
                .actionGet();
    }

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.typeFilter("user");
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);
        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList:"+photoList);
    }

}

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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/script-filter.html</a>
 * 脚本,该章节比较重要
 * @author lei.fang
 * @since 2017/3/8
 */
public class ScriptFilterDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        FilterBuilder filterBuilder = FilterBuilders.scriptFilter(
                "doc['rating'].value > param"
        ).addParam("param",70.0d);

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);

    }
}

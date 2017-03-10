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
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/terms-filter.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class TermsFilterDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        FilterBuilder filterBuilder = FilterBuilders.termsFilter(
                "resourceType",
                "0",
                "1"
        ).execution("plain") //执行模型could be plain, fielddata, bool, and, or, bool_nocache, and_nocache or or_nocache
                ;

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);

    }
}

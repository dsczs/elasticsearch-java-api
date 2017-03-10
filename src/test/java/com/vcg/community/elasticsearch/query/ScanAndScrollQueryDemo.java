package com.vcg.community.elasticsearch.query;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class ScanAndScrollQueryDemo extends ElasticDemo {

    @Test
    public void testScanAndScroll(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withIndices("index_v1")
                .withTypes("resource")
                .withPageable(new PageRequest(0,1))
                .build();
        String scrollId = elasticsearchTemplate.scan(searchQuery,1000,false);
        System.out.println("scrollId:"+scrollId);
    }

}

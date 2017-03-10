package com.vcg.community.elasticsearch.query;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/has-child-parent.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class HasChildQueryDemo  extends ElasticDemo {


    @Test
    public void testHasChildQuery(){

        QueryBuilder qb = QueryBuilders.hasChildQuery("comment",QueryBuilders.termQuery("uname","medcl"));

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,4);

        searchQuery.setPageable(pageRequest);

        List<String> ids = elasticsearchTemplate.queryForIds(searchQuery);

        System.out.println("ids："+ids);
    }

}

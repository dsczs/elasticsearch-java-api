package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/dismax.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class DismaxQueryDemo  extends ElasticDemo {

    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.termQuery("title","苹果"))
                .add(QueryBuilders.termQuery("title","测试"))
                .boost(1.2f)
                .tieBreaker(0.7f);

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,4);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }

}

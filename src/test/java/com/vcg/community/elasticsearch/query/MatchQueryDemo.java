package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/match.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class MatchQueryDemo  extends ElasticDemo {

    @Test
    public void test(){

        QueryBuilder qb = QueryBuilders.matchQuery(
                "title",
                " 苹果"
        );

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }

    @Test
    public void testQueryTitle(){
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(
                "title",
                " 苹果"
        );

        matchQueryBuilder.boost(10);

        SearchQuery searchQuery = new NativeSearchQuery(matchQueryBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

        matchQueryBuilder.boost(1);

    }


    @Test
    public void test1(){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(
                "name",
                " 狐狸"
        );

        SearchResponse searchResponse = client.prepareSearch("students").setTypes("student").setQuery(queryBuilder).execute().actionGet();
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit searchHitFields : hits){
            System.out.println(searchHitFields.getSource());
        }
    }

}

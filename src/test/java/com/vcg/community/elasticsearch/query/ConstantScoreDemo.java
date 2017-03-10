package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/constant-score.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class ConstantScoreDemo extends ElasticDemo {


    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.boolQuery()
                //只要匹配到了title=红色苹果,则所有文档的评分是一样的
                .should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery("title","红色苹果")).boost(100.0F))
                .should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery("title","测试")).boost(10.0F))
                .should(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery("title","彩虹")).boost(1.0F));

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,4);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);


    }

}

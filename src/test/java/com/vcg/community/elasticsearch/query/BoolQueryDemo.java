package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/bool.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class BoolQueryDemo extends ElasticDemo {

    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.boolQuery()
                //查询title分词后,包含苹果的文档
                .must(QueryBuilders.termQuery("title","苹果"))
                .must(QueryBuilders.termsQuery("description","红色","苹果"))
                .must(QueryBuilders.termQuery("uploaderId","89ff72f764b0483517ed7e6016f3d9874"))
                .mustNot(QueryBuilders.termQuery("state",-1))
                .should(QueryBuilders.termQuery("resourceType",0));


        SearchQuery searchQuery = new NativeSearchQuery(qb);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }

}

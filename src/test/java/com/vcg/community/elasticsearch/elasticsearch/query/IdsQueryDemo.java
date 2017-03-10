package com.vcg.community.elasticsearch.elasticsearch.query;

import com.vcg.community.elasticsearch.model.px500.Photo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/ids.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class IdsQueryDemo extends ElasticDemo {

    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.idsQuery().ids(
                "ea777c492635464ba0b5404554884ee9",
                "8901d7e4140440ce9412526f32e45a3c"
        );

        SearchQuery searchQuery = new NativeSearchQuery(qb);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }

}

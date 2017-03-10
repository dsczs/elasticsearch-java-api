package com.vcg.community.elasticsearch.query;

import com.vcg.community.model.px500.Photo;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see<a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/flt.html</a>
 * @see<a>https://www.elastic.co/guide/en/elasticsearch/reference/1.5/query-dsl-flt-field-query.html</a>
 * @author lei.fang
 * @since 2016/12/22
 */
public class FuzzyLikeThisDemo  extends ElasticDemo {

    @Test
    public void test(){
        QueryBuilder qb = QueryBuilders.fuzzyLikeThisFieldQuery("title")
                .analyzer("ik")  //ik分词器 The analyzer that will be used to analyze the text. Defaults to the analyzer associated with the field.
                .likeText("红色苹果") //The text to find documents like it, required
                .ignoreTF(true) //Should term frequency be ignored. Defaults to false
                .maxQueryTerms(12) //The maximum number of query terms that will be included in any generated query. Defaults to 25
                .fuzziness(Fuzziness.AUTO) //The fuzziness of the term variants. Defaults to 0.5
                .prefixLength(0)//Length of required common prefix on variant terms. Defaults to 0
                .boost(1.0f) //Sets the boost value of the query. Defaults to 1.0.
                ;
        SearchQuery searchQuery = new NativeSearchQuery(qb);

        PageRequest pageRequest = new PageRequest(0,4);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);

    }


}

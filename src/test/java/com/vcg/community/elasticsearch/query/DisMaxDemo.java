package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * 为了方便,所有的demo均是junit demo
 * dis_max查询相关demo
 * @author lei.fang
 * @since 2016/12/21
 */

public class DisMaxDemo extends ElasticDemo {



    @Test
    public void testDisMax(){

        //match_phrase 最内层query
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("keywords.keyword","香蕉");

        //内层function_score
        FunctionScoreQueryBuilder innerFunctionScoreBuilder =  new FunctionScoreQueryBuilder(matchQueryBuilder);
        innerFunctionScoreBuilder
                .add(ScoreFunctionBuilders.scriptFunction("doc['keywords.score'].value")
                        .lang("expression"))
                .boostMode(CombineFunction.REPLACE);

        //nested-query
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("keywords",innerFunctionScoreBuilder).boost(11);

        //dis_max
        DisMaxQueryBuilder disMaxQueryBuilder = new DisMaxQueryBuilder();
        disMaxQueryBuilder.add(nestedQueryBuilder);
        disMaxQueryBuilder.boost(10);

        //外层function_score
        FunctionScoreQueryBuilder outerFunctionScoreBuilder = new FunctionScoreQueryBuilder(disMaxQueryBuilder);
        outerFunctionScoreBuilder.boostMode(CombineFunction.REPLACE);


        //构建查询
        SearchQuery searchQuery = new NativeSearchQuery(outerFunctionScoreBuilder);
        PageRequest pageRequest = new PageRequest(0,20);
        searchQuery.setPageable(pageRequest);

        List<Photo> photoList =  elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);

    }



}

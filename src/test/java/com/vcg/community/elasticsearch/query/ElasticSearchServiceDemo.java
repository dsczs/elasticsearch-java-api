package com.vcg.community.elasticsearch.query;

import com.alibaba.fastjson.JSON;
import com.vcg.community.model.px500.Photo;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.index.query.functionscore.script.ScriptScoreFunctionBuilder;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/22
 */
public class ElasticSearchServiceDemo extends ElasticDemo {

    @Test
    public void test(){
        //match_phrase 最内层query
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("keywords.keyword","苹果");

        ScriptScoreFunctionBuilder scriptScoreFunctionBuilder = ScoreFunctionBuilders.scriptFunction("doc['keywords.score'].value").lang("expression");

        //内层function_score
        FunctionScoreQueryBuilder functionScoreQueryBuilder =  new FunctionScoreQueryBuilder(matchQueryBuilder).boostMode(CombineFunction.REPLACE);
        functionScoreQueryBuilder.add(scriptScoreFunctionBuilder);

        //nested-query
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("keywords",functionScoreQueryBuilder);

        //构建查询
        SearchQuery searchQuery = new NativeSearchQuery(nestedQueryBuilder);

        PageRequest pageRequest = new PageRequest(1,10);

        searchQuery.setPageable(pageRequest);


        List<Photo> photoList =  elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+ JSON.toJSONString(photoList));

    }

    @Test
    public void testMatch(){
        //match_phrase 最内层query
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("keywords.keyword","苹果");
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
        //外层function_score
        FunctionScoreQueryBuilder outerFunctionScoreBuilder = new FunctionScoreQueryBuilder(disMaxQueryBuilder);
        outerFunctionScoreBuilder.boostMode(CombineFunction.REPLACE);
        //构建查询
        SearchQuery searchQuery = new NativeSearchQuery(outerFunctionScoreBuilder);
        System.out.println(elasticsearchTemplate.count(searchQuery,Photo.class));;
    }

}

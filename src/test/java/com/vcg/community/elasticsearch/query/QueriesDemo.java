package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
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
public class QueriesDemo extends ElasticDemo {


    @Test
    public void test(){

        //match_phrase 最内层query
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchPhraseQuery("keywords.keyword","红色苹果");

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

        SearchQuery searchQuery = new NativeSearchQuery(outerFunctionScoreBuilder);
        PageRequest pageRequest = new PageRequest(0,20);
        searchQuery.setPageable(pageRequest);

        List<Photo> photoList =  elasticsearchTemplate.queryForList(searchQuery, Photo.class);
        System.out.println("photoList："+photoList);

    }


    @Test
    public void testQuery(){

        String key = "香蕉";
        MatchQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("keywords.keyword",key);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("keywords.keyword",key);

        ScriptScoreFunctionBuilder scriptScoreFunctionBuilder = new ScriptScoreFunctionBuilder();
        scriptScoreFunctionBuilder.lang("expression");
        scriptScoreFunctionBuilder.script("doc['keywords.score'].value");

        DisMaxQueryBuilder disMaxQueryBuilder = new DisMaxQueryBuilder();
        disMaxQueryBuilder.boost(1.2f);
        disMaxQueryBuilder.tieBreaker(0.7f);

        //精确查找
        FunctionScoreQueryBuilder innerFunctionScoreQueryBuilder1 = new FunctionScoreQueryBuilder(matchPhraseQueryBuilder);
        innerFunctionScoreQueryBuilder1.boost(11);
        innerFunctionScoreQueryBuilder1 .boostMode(CombineFunction.REPLACE);
        NestedQueryBuilder matchPhraseNestedQueryBuilder = QueryBuilders.nestedQuery("keywords",innerFunctionScoreQueryBuilder1);
        innerFunctionScoreQueryBuilder1.add(scriptScoreFunctionBuilder);


        //字段匹配,只要有相似度就算匹配
        FunctionScoreQueryBuilder innerFunctionScoreQueryBuilder2 = new FunctionScoreQueryBuilder(matchQueryBuilder);
        innerFunctionScoreQueryBuilder2.boost(5);
        innerFunctionScoreQueryBuilder2 .boostMode(CombineFunction.REPLACE);
        NestedQueryBuilder matchNestedQueryBuilder = QueryBuilders.nestedQuery("keywords",innerFunctionScoreQueryBuilder2);
        innerFunctionScoreQueryBuilder2.add(scriptScoreFunctionBuilder);
        //添加queries
        disMaxQueryBuilder.add(matchPhraseNestedQueryBuilder);
        disMaxQueryBuilder.add(matchNestedQueryBuilder);

        //function_score中添加dis_max
        FunctionScoreQueryBuilder functionScoreQueryBuilder = new FunctionScoreQueryBuilder(disMaxQueryBuilder);



        SearchQuery searchQuery = new NativeSearchQuery(functionScoreQueryBuilder);

        PageRequest pageRequest = new PageRequest(0,20);

        searchQuery.setPageable(pageRequest);

        List<Photo> photoList =  elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList："+photoList);


    }
}

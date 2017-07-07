package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;

/**
 * @see <a>https://doc.oschina.net/es-500px.me?t=148212</a>
 * 注意的地方：elasticsearch 1.X 版本中只支持expression,而不支持
 * @author lei.fang
 * @since 2016/12/21
 */
public class FunctionScoreDemo extends ElasticDemo {

    @Test
    public void testFunctionScore(){
        /**
         * 1.构建FunctionScoreQueryBuilder
         * 2.
         */
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("title","红色苹果"))
        );
        queryBuilder.add(ScoreFunctionBuilders.scriptFunction("_score/2").lang("expression"))
                                .scoreMode("first")
                                .boostMode(CombineFunction.REPLACE);

        List<Photo> photoList =  elasticsearchTemplate.queryForList(new NativeSearchQuery(queryBuilder), Photo.class);
        System.out.println("photoList："+photoList);

    }

    @Test
    public void testNestedFunctionScore(){
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
                        QueryBuilders.nestedQuery("keywords",QueryBuilders.matchPhraseQuery("keywords.keyword","红色苹果"))
        );
        queryBuilder.add(ScoreFunctionBuilders.scriptFunction("doc['keywords.score'].value").lang("expression"))
                .scoreMode("first")
                .boostMode(CombineFunction.REPLACE);
        List<Photo> photoList =  elasticsearchTemplate.queryForList(new NativeSearchQuery(queryBuilder), Photo.class);
        System.out.println("photoList："+photoList);
    }

}

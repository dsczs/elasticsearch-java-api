package org.visualchina.elasticsearch.api.demo.builder;

import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;

/**
 * {@link FunctionScoreQueryBuilder} demo
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-31
 */
public class FunctionScoreQueryBuilderDemo {

    @Test
    public void testFunctionScoreQueryBuilder() throws Exception {


        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                QueryBuilders.functionScoreQuery(
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("name",""), ScoreFunctionBuilders.weightFactorFunction(1000)),
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery("descript",""), ScoreFunctionBuilders.weightFactorFunction(500))
                        }
                ) .scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).setMinScore(0.0F);


    }
}

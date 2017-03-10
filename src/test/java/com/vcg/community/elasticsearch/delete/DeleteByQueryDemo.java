package com.vcg.community.elasticsearch.delete;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/delete-by-query.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class DeleteByQueryDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        DeleteByQueryResponse response = client.prepareDeleteByQuery("index")
                .setQuery(QueryBuilders.termQuery("title", "插画"))
                .execute()
                .actionGet();

        System.out.println("size:"+response.contextSize());
    }
}

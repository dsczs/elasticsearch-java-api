package com.vcg.community.elasticsearch.filter;

import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/query-filter.html</a>
 * 不建议使用
 * @author lei.fang
 * @since 2017/3/8
 */
@Deprecated
public class QueryFilterDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        FilterBuilder filterBuilder = FilterBuilders.queryFilter(
                QueryBuilders.queryString("美女 AND 豪车 OR 插画")
        );
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }
}

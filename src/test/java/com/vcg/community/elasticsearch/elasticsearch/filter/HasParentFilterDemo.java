package com.vcg.community.elasticsearch.elasticsearch.filter;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.HasParentFilterBuilder;
import org.junit.Test;

/**
 * @see  <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/has-child-parent-filter.html</a>
 * @author lei.fang
 * @since 2017/3/8
 */
public class HasParentFilterDemo extends BaseDemo {

    @Test
    public void test(){
        HasParentFilterBuilder hasParentFilterBuilder = FilterBuilders.hasParentFilter(
                "blog",
                FilterBuilders.termFilter("tag","something")
        );
    }


}

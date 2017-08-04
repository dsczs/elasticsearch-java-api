package org.visualchina.elasticsearch.api.demo.index;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;
import org.visualchina.elasticsearch.api.mapping.Blog;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-03
 */
public class MultifieldsIndexDemo extends XPackBaseDemo {

    @Test
    public void testForElasticsearchTemplate() throws Exception {

        elasticsearchTemplate.createIndex(Blog.class);
        elasticsearchTemplate.putMapping(Blog.class);


    }
}

package org.visualchina.elasticsearch.api.demo.query;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;
import org.visualchina.elasticsearch.api.mapping.Photo;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-18
 */
public class HashChildQueryDemo extends BaseDemo{

    @Test
    public void test() throws Exception {
       elasticsearchTemplate.deleteIndex(Photo.class);
    }
}

package org.visualchina.elasticsearch.api.demo.index;

import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;
import org.visualchina.elasticsearch.api.mapping.Blog;

/**
 * @see <a href='https://www.elastic.co/guide/cn/elasticsearch/guide/current/using-language-analyzers.html'></a>
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

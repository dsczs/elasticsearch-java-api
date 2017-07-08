package org.visualchina.elasticsearch.api.demo.get;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-08
 */
public class GetDemo extends BaseDemo {

    @Test
    public void testForClient() throws Exception {
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
    }
}

package org.visualchina.elasticsearch.api.demo.delete;

import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-08
 */
public class DeleteDemo extends BaseDemo {

    @Test
    public void testForClient() throws Exception {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "1").get();
    }
}

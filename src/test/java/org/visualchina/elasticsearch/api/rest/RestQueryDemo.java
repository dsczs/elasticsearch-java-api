package org.visualchina.elasticsearch.api.rest;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
public class RestQueryDemo extends XPackBaseDemo{

    @Test
    public void testCatApi() throws Exception {
        String method = "GET";
        String endpoint = "/_cat";
        Response response = restClient.performRequest(method,endpoint);
    }
}

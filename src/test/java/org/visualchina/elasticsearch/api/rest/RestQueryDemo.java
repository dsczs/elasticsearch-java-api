package org.visualchina.elasticsearch.api.rest;


import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;

import java.io.IOException;
import java.util.Collections;

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
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testCreateIndex() throws Exception {
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testCreateDocument() throws Exception {
        String method = "PUT";
        String endpoint = "/twitter/tweet/1";
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"user\" : \"kimchy\",\n" +
                        "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                        "    \"message\" : \"trying out Elasticsearch\"\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint, Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testGetDocument() throws Exception {
        String method = "GET";
        String endpoint = "/twitter/tweet/1";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testQueryAll() throws Exception {
        String method = "POST";
        String endpoint = "/twitter/tweet/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testQueryByField() throws Exception {
        String method = "POST";
        String endpoint = "/twitter/tweet/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"user\": \"kimchy\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testUpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/twitter/tweet/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testGeoBoundingBox() throws IOException {
        String method = "POST";
        String endpoint = "/attractions/restaurant/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  },\n" +
                "  \"post_filter\": {\n" +
                "    \"geo_bounding_box\": {\n" +
                "      \"location\": {\n" +
                "        \"top_left\": {\n" +
                "          \"lat\": 39.990481,\n" +
                "          \"lon\": 116.277144\n" +
                "        },\n" +
                "        \"bottom_right\": {\n" +
                "          \"lat\": 39.927323,\n" +
                "          \"lon\": 116.405638\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}

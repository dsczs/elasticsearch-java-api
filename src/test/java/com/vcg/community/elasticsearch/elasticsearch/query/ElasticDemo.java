package com.vcg.community.elasticsearch.elasticsearch.query;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 同时测试client+elasticsearchTemplate的demo
 * @author lei.fang
 * @since 2016/12/21
 */
public class ElasticDemo {

    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before(){
        client = new TransportClient();
        client.addTransportAddress(new InetSocketTransportAddress("123.57.68.250", 9300));
        elasticsearchTemplate = new ElasticsearchTemplate(client);
    }


}

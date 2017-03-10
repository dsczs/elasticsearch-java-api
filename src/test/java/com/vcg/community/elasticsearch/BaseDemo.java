package com.vcg.community.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author lei.fang
 * @since 2017/3/7
 */
public class BaseDemo {

    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void setUp(){
        client = new TransportClient();
        client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        elasticsearchTemplate = new ElasticsearchTemplate(client);
    }
}

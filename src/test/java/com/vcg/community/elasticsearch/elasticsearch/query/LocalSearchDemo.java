package com.vcg.community.elasticsearch.elasticsearch.query;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class LocalSearchDemo {



    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before(){
        client = new TransportClient();
        client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
        elasticsearchTemplate = new ElasticsearchTemplate(client);
    }

}

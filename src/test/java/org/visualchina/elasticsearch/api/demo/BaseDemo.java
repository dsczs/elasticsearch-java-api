package org.visualchina.elasticsearch.api.demo;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Before;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

/**
 * Elasticsearch 5.5.0 的client 和 ElasticsearchTemplate的初始化
 * @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.5/transport-client.html'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-07
 */
public class BaseDemo {

    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void setUp() throws Exception {
        client = TransportClient.builder().build();
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300)) ;
        elasticsearchTemplate = new ElasticsearchTemplate(client);
    }


}

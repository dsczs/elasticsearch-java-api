package org.visualchina.elasticsearch.api.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
public class XPackBaseDemo {
    protected TransportClient client;
    protected ElasticsearchTemplate elasticsearchTemplate;
    protected RestClient restClient ;

    @Before
    public void setUp() throws Exception {
        /**
         * 如果es集群安装了x-pack插件则以此种方式连接集群
         * 1. java客户端的方式是以tcp协议在9300端口上进行通信
         * 2. http客户端的方式是以http协议在9200端口上进行通信
         */
        Settings settings = Settings.builder(). put("xpack.security.user", "elastic:changeme").build();
        client = new PreBuiltXPackTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        elasticsearchTemplate = new ElasticsearchTemplate(client);
        restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
    }

    @Test
    public void testConnection() throws Exception {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("Sean Archer");
        ActionFuture<AnalyzeResponse> analyzeResponseActionFuture =  elasticsearchTemplate.getClient().admin().indices().analyze(analyzeRequest);
        System.out.println(analyzeResponseActionFuture.actionGet().getTokens());
    }

}

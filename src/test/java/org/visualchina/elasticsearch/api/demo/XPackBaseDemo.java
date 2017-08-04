package org.visualchina.elasticsearch.api.demo;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.Response;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        restClient = RestClient.builder(new HttpHost("localhost",9200)).setDefaultHeaders(new Header[]{new BasicHeader("xpack.security.user","elastic:changeme")}).build();
    }


    @Test
    public void testClientConnection() throws Exception {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("中华人民共和国");
        ActionFuture<AnalyzeResponse> analyzeResponseActionFuture = client.admin().indices().analyze(analyzeRequest);
        List<AnalyzeResponse.AnalyzeToken> analyzeTokens =  analyzeResponseActionFuture.actionGet().getTokens();
        for (AnalyzeResponse.AnalyzeToken analyzeToken  : analyzeTokens){
            System.out.println(analyzeToken.getTerm());
        }
    }

    @Test
    public void testElasticsearchTemplateConnection() throws Exception {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("中华人民共和国");
        ActionFuture<AnalyzeResponse> analyzeResponseActionFuture =  elasticsearchTemplate.getClient().admin().indices().analyze(analyzeRequest);
        List<AnalyzeResponse.AnalyzeToken> analyzeTokens =  analyzeResponseActionFuture.actionGet().getTokens();
        for (AnalyzeResponse.AnalyzeToken analyzeToken  : analyzeTokens){
            System.out.println(analyzeToken.getTerm());
        }
    }

    @Test
    public void testRestClientConnection() throws Exception {
        String method = "GET";
        String endpoint = "/_analyze";
        Map<String, String> params = new HashMap<>();
        params.put("analyzer","standard");
        params.put("text","中华人民共和国");
        Response response = restClient.performRequest(method,endpoint,params);
        System.out.println(JSON.toJSONString(response.getEntity()));
    }
}

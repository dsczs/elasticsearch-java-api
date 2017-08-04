package org.visualchina.elasticsearch.api.settings;

import org.elasticsearch.Build;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.XPackBaseDemo;

/**
 * 自定义分词器的行为,spring-data-elasticsearch本身提供的ElasticsearchTemplate并不具备修改es的设置,而是获取es底层的client来进行修改
 * @see <a href='https://www.elastic.co/guide/cn/elasticsearch/guide/current/configuring-language-analyzers.html'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
public class CustomAnalysisDemo extends XPackBaseDemo {

    @Test
    public void test() throws Exception {

        String settings = "{\n" +
                "  \"settings\": {\n" +
                "    \"analysis\": {\n" +
                "      \"analyzer\": {\n" +
                "        \"my_english\": {\n" +
                "          \"type\": \"english\",\n" +
                "          \"stem_exclusion\": [ \"organization\", \"organizations\" ], \n" +
                "          \"stopwords\": [ \n" +
                "            \"a\", \"an\", \"and\", \"are\", \"as\", \"at\", \"be\", \"but\", \"by\", \"for\",\n" +
                "            \"if\", \"in\", \"into\", \"is\", \"it\", \"of\", \"on\", \"or\", \"such\", \"that\",\n" +
                "            \"the\", \"their\", \"then\", \"there\", \"these\", \"they\", \"this\", \"to\",\n" +
                "            \"was\", \"will\", \"with\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        elasticsearchTemplate.createIndex("my_index",settings);


    }
}

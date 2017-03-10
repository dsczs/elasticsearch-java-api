package com.vcg.community.elasticsearch.elasticsearch.update;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.action.update.UpdateRequest;
import org.junit.Test;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/java-update-api-script.html</a>
 *  只支持groovy,不支持expression,经过多次测试
 *  @see com.vcg.community.elasticsearch.elasticsearch.filter.ScriptFilterDemo
 * @author lei.fang
 * @since 2017/3/8
 */
public class UpdateByScriptDemo extends BaseDemo {

    @Test
    public void test() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("index", "resource", "0f21ddb756454da18bc64c86220be2b8")
                .scriptLang("groovy")
                .script("ctx._source.title = \"我是小王八1\"");
        client.update(updateRequest).get();
    }
    //不支持该种写法
    @Test
    public void test1() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("index", "resource", "0f21ddb756454da18bc64c86220be2b8")
               .scriptLang("expression")
                .script("doc['title'].value = \"我是小王八\"");
        client.update(updateRequest).get();
    }

}

package com.vcg.community.elasticsearch.elasticsearch.percolator;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import java.io.IOException;

/**
 * @see <a>http://blog.csdn.net/likui1314159/article/details/49964585</a>
 * @see <a>http://www.tuicool.com/articles/6fABRbZ</a>
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/percolate.html#percolate</a>
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/reference/1.5/search-percolate.html</a>
 * @see <a>https://berlinbuzzwords.de/session/elasticsearch-percolator</a>
 * 该章节属于高级教程，一般用于警报操作设置,事件监控,数据路由,数据分类
 * @author lei.fang
 * @since 2017/3/8
 */
public class PercolatorDemo extends BaseDemo{


    @Test
    public void test() throws Exception {

    }

    public void  percolator(String IndexName,String indexType) {

        //This is the query we're registering in the percolator
        //创建查询条件，并把该查询条件注册到索引中
        QueryBuilder qb = QueryBuilders.termQuery("areaName", "宝安");
        try {
            //Index the query = register it in the percolator
            //把查询条件添加到索引中，myDesignatedQueryName为定义的查询名
            client.prepareIndex(IndexName, ".percolator", "myDesignatedQueryName")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            // Register the query，添加查询记录
                            .field("query", qb)
                            .endObject())
                    .setRefresh(true) // Needed when the query shall be available immediately
                    .execute().actionGet();
            //上面的term查询定义名为：myDesignatedQueryName

            //为了验证一个文档是否符合该查询，创建以下代码：构建一个文档
            //Build a document to check against the percolator
            XContentBuilder docBuilder = XContentFactory.jsonBuilder().startObject();
            //This is needed to designate the document
            docBuilder.field("doc").startObject();
            docBuilder.field("areaName", "宝安");
            docBuilder.endObject(); //End of the doc field
            docBuilder.endObject(); //End of the JSON root object

            //Percolate查询
            PercolateResponse response = client.preparePercolate()
                    .setIndices(IndexName)
                    //文档创建时的mapping配置信息名，
                    //如果未修改的话就是索引的type信息
                    .setDocumentType(indexType)
                    //docBuilder构建的文档
                    .setSource(docBuilder).execute().actionGet();

            //Iterate over the results,迭代代码结果，结果处理
            //获取查询query后处理逻辑
            for (PercolateResponse.Match match : response) {
                //创建percolate时指定的ID，根据查询ID在做相应的操作吧
                System.out.println("percolate ID: " + match.getId());
                System.out.println("percolate Index Name: " + match.getIndex());
                //Handle the result which is the name of
                //the query in the percolator
            }
        } catch (ElasticsearchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

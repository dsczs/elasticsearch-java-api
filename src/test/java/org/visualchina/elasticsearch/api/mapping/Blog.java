package org.visualchina.elasticsearch.api.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * 使用spring-data-elasticsearch来构建multifields（多字段）
 * @see <a href='https://www.elastic.co/guide/cn/elasticsearch/guide/current/using-language-analyzers.html'></a>
 * @see <a href='https://stackoverflow.com/questions/24265511/multifield-mapping-using-spring-data-elasticsearch-annotations'></a>
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
@Document(indexName = "my_index",type = "blog",replicas = 0)
public class Blog {

    @Id
    private String id;

    @MultiField(
            mainField = @Field(type = FieldType.text),
            otherFields = {
                    @InnerField(suffix = "exact",type = FieldType.keyword,index = false),
                    @InnerField(suffix = "english",type = FieldType.text,indexAnalyzer = "english")
            }
        )


    private String title;

    public Blog() {
    }

    public Blog(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

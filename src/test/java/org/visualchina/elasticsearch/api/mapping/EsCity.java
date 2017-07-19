package org.visualchina.elasticsearch.api.mapping;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-19
 */
@Document(indexName = "apn_index_city", type = "city",replicas = 0)
public class EsCity {

    @Field(type = FieldType.String,searchAnalyzer = "standard", analyzer = "standard",store = true)
    private String id;

    @Field(type = FieldType.String,searchAnalyzer = "standard", analyzer = "standard",store = true)
    private String name;

    @Field(type = FieldType.String,store = true)
    @Parent(type = "province")
    private String provinceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}

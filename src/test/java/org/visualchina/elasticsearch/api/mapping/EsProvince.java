package org.visualchina.elasticsearch.api.mapping;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

import java.util.List;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-19
 */
@Document(indexName = "apn_index", type = "province")
public class EsProvince {

    @Id
    private String id;

    @Field(type = FieldType.text,searchAnalyzer = "standard", analyzer = "standard",store = true)
    private String name;

    @Field(type = FieldType.text,store = true)
    @Parent(type = "country")
    private String countryId;

    @Field( type = FieldType.Nested)
    private List<EsCity> cities;

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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public List<EsCity> getCities() {
        return cities;
    }

    public void setCities(List<EsCity> cities) {
        this.cities = cities;
    }
}

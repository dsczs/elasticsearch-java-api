package org.visualchina.elasticsearch.api.demo.parent;

import org.junit.Test;
import org.visualchina.elasticsearch.api.demo.BaseDemo;
import org.visualchina.elasticsearch.api.mapping.EsCity;
import org.visualchina.elasticsearch.api.mapping.EsCountry;
import org.visualchina.elasticsearch.api.mapping.EsProvince;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-19
 */
public class PutParentChildMappingDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        elasticsearchTemplate.deleteIndex(EsCountry.class);
        elasticsearchTemplate.deleteIndex(EsProvince.class);
        elasticsearchTemplate.deleteIndex(EsCity.class);

        elasticsearchTemplate.createIndex(EsCountry.class);
        elasticsearchTemplate.createIndex(EsProvince.class);
        elasticsearchTemplate.createIndex(EsCity.class);

        elasticsearchTemplate.putMapping(EsCity.class);
        elasticsearchTemplate.putMapping(EsProvince.class);
        elasticsearchTemplate.putMapping(EsCountry.class);

    }
}

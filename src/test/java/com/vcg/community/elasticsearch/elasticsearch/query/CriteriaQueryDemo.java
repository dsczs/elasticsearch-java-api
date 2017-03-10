package com.vcg.community.elasticsearch.elasticsearch.query;

import com.alibaba.fastjson.JSON;
import com.vcg.community.elasticsearch.model.px500.Photo;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class CriteriaQueryDemo extends ElasticDemo {

    @Test
    public void testCriteriaQuery(){
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("title").contains("test"));
        List<Photo> photos = elasticsearchTemplate.queryForList(criteriaQuery, Photo.class);
        System.out.println("photos:"+ JSON.toJSONString(photos));
    }

}

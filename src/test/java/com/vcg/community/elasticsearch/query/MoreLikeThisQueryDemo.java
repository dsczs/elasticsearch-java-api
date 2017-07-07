package com.vcg.community.elasticsearch.query;

import com.vcg.community.mapping.Photo;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.MoreLikeThisQuery;

/**
 * @author lei.fang
 * @since 2016/12/29
 */
public class MoreLikeThisQueryDemo extends ElasticDemo {

    @Test
    public void testMoreLikeThisQuery(){
        MoreLikeThisQuery moreLikeThisQuery = new MoreLikeThisQuery();
        moreLikeThisQuery.setId("5f09e3aa37744c6b8df456c812f77009");
        moreLikeThisQuery.addFields("title");
        moreLikeThisQuery.setMinDocFreq(1);

        Page <Photo> page = elasticsearchTemplate.moreLikeThis(moreLikeThisQuery, Photo.class);

        System.out.println("photos:"+page.getContent());
    }

}

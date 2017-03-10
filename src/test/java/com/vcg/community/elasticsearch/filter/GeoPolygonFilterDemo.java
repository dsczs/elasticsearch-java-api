package com.vcg.community.elasticsearch.filter;

import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.model.px500.Photo;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see  <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/geo-poly-filter.html</a>
 * 多边形内的点的集合，该例子只是三角形，可以使用多个点
 * @author lei.fang
 * @since 2017/3/8
 */
public class GeoPolygonFilterDemo extends BaseDemo {


    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.geoPolygonFilter("geoCoordinates")
                .addPoint(31.709730392685895,118.85877310455784)
                .addPoint(32.709730392685895,118.85877310455784)
                .addPoint(33.709730392685895,118.85877310455784);

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);

    }

}

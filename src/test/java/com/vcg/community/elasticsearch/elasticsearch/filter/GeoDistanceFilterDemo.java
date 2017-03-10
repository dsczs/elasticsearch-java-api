package com.vcg.community.elasticsearch.elasticsearch.filter;

import com.vcg.community.elasticsearch.elasticsearch.BaseDemo;
import com.vcg.community.elasticsearch.model.px500.Photo;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see  <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/geo-distance-filter.html</a>
 * 查看距离某个点多少距离的点的集合
 * @author lei.fang
 * @since 2017/3/8
 */
public class GeoDistanceFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.geoDistanceFilter("geoCoordinates")
                .point(33.709730392685895,118.85877310455784)
                .distance(10000, DistanceUnit.KILOMETERS)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }

}

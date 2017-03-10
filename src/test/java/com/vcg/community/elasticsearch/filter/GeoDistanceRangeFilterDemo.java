package com.vcg.community.elasticsearch.filter;

import com.vcg.community.elasticsearch.BaseDemo;
import com.vcg.community.model.px500.Photo;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @see <a>https://www.elastic.co/guide/en/elasticsearch/client/java-api/1.5/geo-distance-range-filter.html</a>
 * 查看距离某个点的距离在某个范围内的点的集合
 * @author lei.fang
 * @since 2017/3/8
 */
public class GeoDistanceRangeFilterDemo extends BaseDemo {

    @Test
    public void test(){
        FilterBuilder filterBuilder = FilterBuilders.geoDistanceRangeFilter("geoCoordinates")
                .point(33.709730392685895,118.85877310455784)
                .from("200km") //单位必须是小写哦，该版本暂时没有枚举支持单位
                .to("400km")
                .includeLower(true)
                .includeUpper(false)
                .optimizeBbox("memory")
                .geoDistance(GeoDistance.ARC);

        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery(),filterBuilder);

        List<Photo> photoList = elasticsearchTemplate.queryForList(searchQuery, Photo.class);

        System.out.println("photoList:"+photoList);
    }

}

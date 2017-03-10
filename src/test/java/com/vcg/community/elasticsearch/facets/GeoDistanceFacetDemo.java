package com.vcg.community.elasticsearch.facets;

import com.vcg.community.elasticsearch.BaseDemo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.geodistance.GeoDistanceFacet;
import org.junit.Test;

/**
 * @author lei.fang
 * @since 2017/3/10
 */
public class GeoDistanceFacetDemo extends BaseDemo {

    @Test
    public void test() throws Exception {

        SearchResponse searchResponse = client.prepareSearch("index")
                .addFacet(
                        FacetBuilders.geoDistanceFacet("f")
                                .field("geoCoordinates")              // Field containing coordinates we want to compare with
                                .point(33.709730392685895,118.85877310455784)                     // Point from where we start (0)
                                .addUnboundedFrom(10)               // 0 to 10 km (excluded)
                                .addRange(10, 20)                   // 10 to 20 km (excluded)
                                .addRange(20, 100)                  // 20 to 100 km (excluded)
                                .addUnboundedTo(100)                // from 100 km to infinity (and beyond ;-) )
                                .unit(DistanceUnit.KILOMETERS)
                )
                .execute()
                .actionGet();

        GeoDistanceFacet f = (GeoDistanceFacet) searchResponse.getFacets().facetsAsMap().get("f");

        for (GeoDistanceFacet.Entry entry : f) {
            System.out.println("from:"+entry.getFrom());            // Distance from requested
            System.out.println("to:"+entry.getTo());             // Distance to requested
           System.out.println( "count:"+entry.getCount());          // Doc count
            System.out.println("min:"+entry.getMin());           // Min value
            System.out.println("max:"+entry.getMax());             // Max value
            System.out.println("totla:"+entry.getTotal());           // Sum of values
            System.out.println("mean:"+entry.getMean());            // Mean
            System.out.println("----------------------");
        }


    }
}

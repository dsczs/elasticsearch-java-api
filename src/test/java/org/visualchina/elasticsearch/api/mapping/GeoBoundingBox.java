package org.visualchina.elasticsearch.api.mapping;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-08-04
 */
@Document(indexName = "attractions",type = "restaurant",replicas = 0,shards = 1)
public class GeoBoundingBox {


    private String name;

    @GeoPointField
    private Location location;

    public GeoBoundingBox(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}



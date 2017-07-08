package org.visualchina.elasticsearch.api.mapping;

/**
 * @author lei.fang
 * @since 2017/3/10
 */
public class GeoCoordinates {

    //经度
    private double  lat;

    //纬度
    private Double lon;

    public GeoCoordinates() {
    }

    public GeoCoordinates(double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}

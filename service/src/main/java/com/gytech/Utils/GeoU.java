package com.gytech.Utils;
import java.util.Map;

/**
 * 常用的一些工具方法
 * Created by LQ on 2018/7/11.
 */
public class GeoU {

    public static class LatLon{
        private Double lat;
        private Double lon;

        public LatLon(Double lat,Double lon){
            this.lat = lat;
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }

    public static float distance(LatLon latLon1,LatLon latLon2) {
        double R = 6378.137;
        double dLat = latLon1.getLat() * Math.PI / 180 - latLon2.getLat() * Math.PI / 180;
        double dLon = latLon1.getLon() * Math.PI / 180 - latLon2.getLon() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(latLon2.getLat() * Math.PI / 180)
                * Math.cos(latLon1.getLat() * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return (float) (d * 1000);
    }


}

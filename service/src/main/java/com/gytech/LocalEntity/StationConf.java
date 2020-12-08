package com.gytech.LocalEntity;

/**
 * Created by LQ on 2018/9/30.
 * com.gytech.LocalEntity
 */
public class StationConf {

    private String thing_id;

    private String base_key;

    private String up_key;

    private String down_key;

    private String name;

    private Double latitude;

    private Double longitude;

    public String getThing_id() {
        return thing_id;
    }

    public void setThing_id(String thing_id) {
        this.thing_id = thing_id;
    }

    public String getBase_key() {
        return base_key;
    }

    public void setBase_key(String base_key) {
        this.base_key = base_key;
    }

    public String getUp_key() {
        return up_key;
    }

    public void setUp_key(String up_key) {
        this.up_key = up_key;
    }

    public String getDown_key() {
        return down_key;
    }

    public void setDown_key(String down_key) {
        this.down_key = down_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

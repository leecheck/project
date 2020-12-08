package com.gytech.LocalEntity;

import com.gytech.entity.admin.SysMaplayer;

import java.util.List;

/**
 * Created by LQ on 2018/9/30.
 * com.gytech.LocalEntity
 */
public class LayerGroup {

    private String name;

    private String id;

    private List<SysMaplayer> layers;

    public LayerGroup(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SysMaplayer> getLayers() {
        return layers;
    }

    public void setLayers(List<SysMaplayer> layers) {
        this.layers = layers;
    }
}

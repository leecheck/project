package com.gytech.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@TableName("sys_maplayer")
public class SysMaplayer extends Model<SysMaplayer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 服务id
     */
    @TableField("layer_id")
    private String layerId;
    /**
     * 名称
     */
    @TableField("layer_name")
    private String layerName;
    /**
     * 地图服务地址
     */
    @TableField("map_url")
    private String mapUrl;
    /**
     * 数据地址
     */
    @TableField("layer_url")
    private String layerUrl;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据源唯一字段key
     */
    @TableField("object_key")
    private String objectKey;
    /**
     * 数据源标题字段
     */
    @TableField("title_field")
    private String titleField;
    /**
     * 展示字段
     */
    @TableField("show_fields")
    private String showFields;
    /**
     * 精简的展示字段
     */
    @TableField("info_fields")
    private String infoFields;
    /**
     * 数据源表名
     */
    @TableField("feature_layer")
    private String featureLayer;
    /**
     * 是否加载
     */
    @TableField("is_load")
    private Integer isLoad;
    /**
     * 所属图层组（字典表读取）
     */
    @TableField("layer_group")
    private String layerGroup;
    /**
     * 查询字段
     */
    @TableField("query_fields")
    private String queryFields;
    /**
     * 开启识别
     */
    @TableField("active_identy")
    private Integer activeIdenty;
    /**
     * 图层类型
     */
    @TableField("layer_type")
    private String layerType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getLayerUrl() {
        return layerUrl;
    }

    public void setLayerUrl(String layerUrl) {
        this.layerUrl = layerUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getTitleField() {
        return titleField;
    }

    public void setTitleField(String titleField) {
        this.titleField = titleField;
    }

    public String getShowFields() {
        return showFields;
    }

    public void setShowFields(String showFields) {
        this.showFields = showFields;
    }

    public String getInfoFields() {
        return infoFields;
    }

    public void setInfoFields(String infoFields) {
        this.infoFields = infoFields;
    }

    public String getFeatureLayer() {
        return featureLayer;
    }

    public void setFeatureLayer(String featureLayer) {
        this.featureLayer = featureLayer;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }

    public String getLayerGroup() {
        return layerGroup;
    }

    public void setLayerGroup(String layerGroup) {
        this.layerGroup = layerGroup;
    }

    public String getQueryFields() {
        return queryFields;
    }

    public void setQueryFields(String queryFields) {
        this.queryFields = queryFields;
    }

    public Integer getActiveIdenty() {
        return activeIdenty;
    }

    public void setActiveIdenty(Integer activeIdenty) {
        this.activeIdenty = activeIdenty;
    }

    public String getLayerType() {
        return layerType;
    }

    public void setLayerType(String layerType) {
        this.layerType = layerType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysMaplayer{" +
        "id=" + id +
        ", layerId=" + layerId +
        ", layerName=" + layerName +
        ", mapUrl=" + mapUrl +
        ", layerUrl=" + layerUrl +
        ", description=" + description +
        ", objectKey=" + objectKey +
        ", titleField=" + titleField +
        ", showFields=" + showFields +
        ", infoFields=" + infoFields +
        ", featureLayer=" + featureLayer +
        ", isLoad=" + isLoad +
        ", layerGroup=" + layerGroup +
        ", queryFields=" + queryFields +
        ", activeIdenty=" + activeIdenty +
        ", layerType=" + layerType +
        "}";
    }
}

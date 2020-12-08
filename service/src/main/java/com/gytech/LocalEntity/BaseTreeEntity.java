package com.gytech.LocalEntity;

import java.util.List;

/**
 * Created by LQ on 2018/9/17.
 * com.gytech.LocalEntity
 */
public class BaseTreeEntity {

    private Long id;

    private Long parent_id;

    private List<BaseTreeEntity> children;

    private BaseTreeEntity parent;

    public List<BaseTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<BaseTreeEntity> children) {
        this.children = children;
    }

    public BaseTreeEntity getParent() {
        return parent;
    }

    public void setParent(BaseTreeEntity parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}

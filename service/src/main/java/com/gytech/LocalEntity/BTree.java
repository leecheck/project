package com.gytech.LocalEntity;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/27.
 * com.gytech.LocalEntity
 */
public class BTree<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 显示节点文本
     */
    private String title;
    /**
     * 节点状态，open closed
     */
    private String state = "open";
    /**
     * 是否展开
     */
    private boolean expand = true;
    /**
     * 节点激活
     */
    private boolean selected = false;
    /**
     * 节点是否被选中 true false
     */
    private boolean checked = false;
    /**
     * 节点属性
     */
    private List<Map<String, Object>> attributes;
    /**
     * 节点的子节点
     */
    private List<BTree<T>> children = new ArrayList<BTree<T>>();

    /**
     * 父ID
     */
    private String parentId;
    /**
     * 是否有父节点
     */
    private boolean isParent = false;
    /**
     * 是否有子节点
     */
    private boolean isChildren = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Map<String, Object>> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Map<String, Object>> attributes) {
        this.attributes = attributes;
    }

    public List<BTree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<BTree<T>> children) {
        this.children = children;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isChildren() {
        return isChildren;
    }

    public void setChildren(boolean isChildren) {
        this.isChildren = isChildren;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BTree(String id, String title, String state, boolean checked,
                List<Map<String, Object>> attributes, List<BTree<T>> children,
                boolean isParent, boolean isChildren, String parentID) {
        super();
        this.id = id;
        this.title = title;
        this.state = state;
        this.checked = checked;
        this.attributes = attributes;
        this.children = children;
        this.isParent = isParent;
        this.isChildren = isChildren;
        this.parentId = parentID;
    }

    public BTree() {
        super();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * 从全的树形结构中返回父节点为pid的子树结构
     * @param pid
     * @return
     */
    public BTree childTree(String pid){
        if (StringUtils.isBlank(pid)){
            return this;
        }
        return dgChildTree(this,pid);
    }

    /**
     * 返回列表
     * @return
     */
    public List<BTree> listTree(){
        return dgChildList(this);
    }

    /**
     * 递归遍历节点
     * @param tree
     * @param pid
     * @return
     */
    private BTree dgChildTree(BTree tree,String pid){
        BTree result = null;
        if (tree.getId().equalsIgnoreCase(pid)){
            result = tree;
            return result;
        }
        List<BTree<T>> child = tree.getChildren();
        if (child.size()==0){
            return null;
        }
        for(BTree<T> childTree:child){
            if (childTree.getParentId().equalsIgnoreCase(pid)){
                result = childTree;
            }
            if (dgChildTree(childTree,pid)!=null){
                result = childTree;
                break;
            }
        }
        return result;
    }

    /**
     * 递归遍历节点
     * @return
     */
    private List<BTree> dgChildList(BTree tree){
        List<BTree> result = new ArrayList<>();
        List<BTree<T>> list = tree.getChildren();
        if(list.size()>0){
            result.addAll(list);
            for(BTree<T> childTree:list){
                result.addAll(dgChildList(childTree));
            }
        }
        return result;
    }
}

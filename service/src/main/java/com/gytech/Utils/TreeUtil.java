package com.gytech.Utils;

import com.gytech.LocalEntity.BTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LQ on 2018/9/17.
 * com.gytech.Utils
 */
public class TreeUtil {


    public static <T> BTree<T> build(List<BTree<T>> nodes) {
        if(nodes == null){
            return null;
        }
        List<BTree<T>> topNodes = new ArrayList<BTree<T>>();
        for (BTree<T> children : nodes) {
            String pid = children.getParentId();
            if (pid == null || "".equals(pid) || "0".equals(pid)) {
                topNodes.add(children);
                continue;
            }
            for (BTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChildren().add(children);
                    children.setParent(true);
                    parent.setChildren(true);
                    continue;
                }
            }
        }
        BTree<T> root = new BTree<T>();
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root.setId("-1");
            root.setParentId("");
            root.setParent(false);
            root.setChildren(true);
            root.setChecked(true);
            root.setChildren(topNodes);
            root.setTitle("顶级节点");
        }
        return root;
    }
}

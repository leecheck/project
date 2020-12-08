package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Configuration.EnvContext;
import com.gytech.Const;
import com.gytech.LocalEntity.BTree;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.Utils.TreeUtil;
import com.gytech.entity.admin.*;
import com.gytech.mapper.admin.SysAreaMapper;
import com.gytech.service.ISysAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements ISysAreaService {

    @Autowired
    private EnvContext envContext;

    @Autowired
    private ISysAreaService iSysAreaService;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysArea>(curPage,pageSize);
        Long parentId = GU.getMapKeyLong("parentId",paramMap);
        String areaName =  GU.getMapKeyString("areaName",paramMap);
        boolean isContainParent = GU.getMapKeyboolean("containParent",paramMap);
        QueryWrapper<SysArea> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(areaName)){
            wrapper.eq("area_name",areaName);
        }
        if (parentId>0){
            wrapper.eq("parent_id",parentId);
            if (isContainParent){
                wrapper.or();
                wrapper.eq("id",parentId);
            }
        }
        pageEntity.setRecords(sysAreaMapper.selectList(wrapper));
        return pageEntity;
    }

    @Override
    public Res add(SysArea sysArea) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        Long parentId = sysArea.getParentId();
        if (parentId==null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        SysArea parent = getById(parentId);
        if (parent == null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        if (parentId.equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
        }
        sysArea.setCreateUserId(currentUser.getId());
        if (save(sysArea)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
    }

    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysArea org = getById(id);
        if (org == null){
            return res.data(false).reason("已不存在id为" + id + "的单位");
        }
        if (org.getId().equals(Const.ROOT_NODE_ID)||org.getParentId().equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_DEL_PARENT);
        }
        QueryWrapper<SysArea> entity = new QueryWrapper<SysArea>();
        entity.eq("parent_id",id);
        int children = baseMapper.selectCount(entity);
        if (children>0){
            return res.reason(ResultInfo.INFO_DEL_HASCHILDREN);
        }
        boolean flag = removeById(id);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res orgTree() {
        Res res = new Res();
        List<SysArea> orgs = baseMapper.selectList(null);
        List<BTree<SysArea>> trees = new ArrayList<BTree<SysArea>>();
        for (SysArea org:orgs){
            BTree<SysArea> tree = new BTree<SysArea>();
            tree.setId(String.valueOf(org.getId()));
            tree.setParentId(String.valueOf(org.getParentId()));
            tree.setTitle(org.getAreaName());
            trees.add(tree);
        }
        BTree<SysArea> orgTree = TreeUtil.build(trees);
        return res.success().data(orgTree);
    }


    @Override
    public Boolean areaCodeDump(String areaCode, Long areaId) {
        QueryWrapper<SysArea> entity = new QueryWrapper<SysArea>();
        if (areaId == null){
            entity.eq("area_code",areaCode);
            List<SysArea> areas = baseMapper.selectList(entity);
            if (areas.size()>0){
                return true;
            }
        }
        entity.eq("area_code",areaCode);
        entity.ne("id",areaId);
        List<SysArea> areas = baseMapper.selectList(entity);
        if (areas.size()>0){
            return true;
        }
        return false;
    }


}

package com.gytech.service.impl;

import cn.hutool.cache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Configuration.EnvContext;
import com.gytech.Const;
import com.gytech.LocalEntity.BTree;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Utils.GU;
import com.gytech.Utils.TreeUtil;
import com.gytech.entity.admin.*;
import com.gytech.mapper.admin.SysOrganizationMapper;
import com.gytech.service.ISysOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements ISysOrganizationService {

    @Autowired
    private EnvContext envContext;

    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    @Autowired
    ISysUserService userService;

    @Autowired
    private Cache<String,Object> sysCache;
    /**
     * containParent参数设置为true，则在按父节点查询的时候把父节点也加入结果集合
     * @param paramMap
     * @return
     */
    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysOrganization>(curPage,pageSize);
        Long parentId = GU.getMapKeyLong("parentId",paramMap);
        String organCode =  GU.getMapKeyString("organCode",paramMap);
        String organName =  GU.getMapKeyString("organName",paramMap);
        boolean isContainParent = GU.getMapKeyboolean("containParent",paramMap);
        QueryWrapper<SysOrganization> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(organCode)){
            wrapper.eq("organ_code",organCode);
        }
        if (StringUtils.isNotBlank(organName)){
            wrapper.eq("organ_name",organName);
        }
        if (parentId>0){
            wrapper.eq("parent_id",parentId);
            if (isContainParent){
                wrapper.or();
                wrapper.eq("id",parentId);
            }
        }
        pageEntity.setRecords(sysOrganizationMapper.selectList(wrapper));
        return pageEntity;
    }

    /**
     * 必须包含parentid，切挂接不到parent不许插入，根节点只允许一个
     * @param organization
     * @return
     */
    @Override
    public Res add(SysOrganization organization) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        Long parentId = organization.getParentId();
        if (parentId==null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        SysOrganization parent = getById(parentId);
        if (parent == null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        if (parentId.equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
        }
        organization.setCreateUserId(currentUser.getId());
        if (organCodeDump(organization.getOrganCode(),null)){
            return res.reason(ResultInfo.INFO_ADD_ATTALREADY);
        }
        if (save(organization)){
            updateTree();
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    /**
     * 根节点不许删除，包含子节点不许删除
     * @param id
     * @return
     */
    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysOrganization org = getById(id);
        if (org == null){
            return res.data(false).reason("已不存在id为" + id + "的单位");
        }
        if (org.getId().equals(Const.ROOT_NODE_ID)||org.getParentId().equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_DEL_PARENT);
        }
        QueryWrapper<SysOrganization> entity = new QueryWrapper<SysOrganization>();
        entity.eq("parent_id",id);
        int children = baseMapper.selectCount(entity);
        if (children>0){
            return res.reason(ResultInfo.INFO_DEL_HASCHILDREN);
        }
        if (removeById(id)){
            updateTree();
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    /**
     * 获取树，暂未考虑用户层级
     * @return
     */
    @Override
    public Res orgTree() {
        Res res = new Res();
        Object objTree = sysCache.get(Const.CACHE_ORG);
        BTree<SysOrganization> orgTree = null;
        if (null == objTree){
            orgTree = updateTree();
        }else {
            orgTree = (BTree<SysOrganization>) objTree;
        }
        Long userOrg = userService.simpleCurrentUser().getOrgId();
        return res.success().data(orgTree.childTree(userOrg.toString()));
    }

    @Override
    public BTree<SysOrganization> cacheTree(){
        Object objTree = sysCache.get(Const.CACHE_ORG);
        BTree<SysOrganization> orgTree = null;
        if (null == objTree){
            orgTree = updateTree();
        }else {
            orgTree = (BTree<SysOrganization>) objTree;
        }
        return orgTree;
    }


    /**
     * 获取树(前后端分离)
     * @return
     */
    @Override
    public Res orgTreeRest(UserInfo user) {
        Res res = new Res();
        BTree<SysOrganization> orgTree = cacheTree();
        Long userOrg = user.getOrgId();
        return res.success().data(orgTree.childTree(userOrg.toString()));
    }

    @Override
    public List<BTree> userOrgList() {
        Long userOrg = userService.simpleCurrentUser().getOrgId();
        Object objTree = sysCache.get(Const.CACHE_ORG);
        BTree<SysOrganization> orgTree = null;
        if (null == objTree){
            orgTree = updateTree();
        }else {
            orgTree = (BTree<SysOrganization>) objTree;
        }
        if (userOrg==null){
            return orgTree.listTree();
        }
        return orgTree.childTree(userOrg.toString()).listTree();
    }

    @PostConstruct
    @Override
    public BTree<SysOrganization> updateTree(){
        List<SysOrganization> orgs = baseMapper.selectList(null);
        List<BTree<SysOrganization>> trees = new ArrayList<BTree<SysOrganization>>();
        for (SysOrganization org:orgs){
            BTree<SysOrganization> tree = new BTree<SysOrganization>();
            tree.setId(String.valueOf(org.getId()));
            tree.setParentId(String.valueOf(org.getParentId()));
            tree.setTitle(org.getOrganName());
            trees.add(tree);
        }
        BTree<SysOrganization> orgTree = TreeUtil.build(trees);
        sysCache.put(Const.CACHE_ORG,orgTree);
        return orgTree;
    }

    @Override
    public Boolean organCodeDump(String organCode,Long organId) {
        QueryWrapper<SysOrganization> entity = new QueryWrapper<SysOrganization>();
        if (organId == null){
            entity.eq("organ_code",organCode);
            List<SysOrganization> orgs = baseMapper.selectList(entity);
            if (orgs.size()>0){
                return true;
            }
        }
        entity.eq("organ_code",organCode);
        entity.ne("id",organId);
        List<SysOrganization> orgs = baseMapper.selectList(entity);
        if (orgs.size()>0){
            return true;
        }
        return false;
    }
}

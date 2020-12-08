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
import com.gytech.mapper.admin.SysMenuMapper;
import com.gytech.mapper.admin.SysRoleMenuMapper;
import com.gytech.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.service.ISysRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
@Transactional
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @Autowired
    private EnvContext envContext;
    @Override
    public List<SysMenu> findByUserId(Long userid) {
        return sysMenuMapper.queryByUserId(userid);
    }

    /***
     * containParent参数设置为true，则在按父节点查询的时候把父节点也加入结果集合
     * @param paramMap
     * @return
     */
    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysMenu>(curPage,pageSize);
        Long parentId = GU.getMapKeyLong("parentId",paramMap);
        String organName =  GU.getMapKeyString("menuName",paramMap);
        boolean isContainParent = GU.getMapKeyboolean("containParent",paramMap);
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(organName)){
            wrapper.eq("menu_name",organName);
            paramMap.put("menu_name",organName);
        }
        if (parentId>0){
            wrapper.eq("parent_id",parentId);
            if (isContainParent){
                wrapper.or();
                wrapper.eq("id",parentId);
            }
        }
        wrapper.orderByAsc("sort");
        pageEntity.setRecords(sysMenuMapper.selectList(wrapper));
        return pageEntity;
    }
    public Boolean childNameDump(String menuName,Long parentId,Long menuId){
        QueryWrapper<SysMenu> entity = new QueryWrapper<SysMenu>();
        if (menuId == null){
            entity.eq("menu_name",menuName).eq("parent_id",parentId);
            List childList = baseMapper.selectList(entity);
            if (childList.size()>0){
                return true;
            }
        }
        entity.eq("menu_name",menuName).eq("parent_id",parentId)
                .ne("id",menuId);
        List<SysMenu> childList= baseMapper.selectList(entity);
        if (childList.size()>0){
            return true;
        }
        return false;
    }
    /***
     * 必须包含parentid，切挂接不到parent不许插入，根节点只允许一个
     * @param sysMenu
     * @return
     */
    @Override
    public Res add(SysMenu sysMenu) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        Long parentId = sysMenu.getParentId();
        if (parentId==null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        SysMenu parent = getById(parentId);
        if (parent == null){
            return res.reason(ResultInfo.INFO_ADD_NOPARENT);
        }
        if (parentId.equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_ADD_AREADYPARENT);
        }
        if (childNameDump(sysMenu.getMenuName(),parentId,sysMenu.getId())){
            return res.reason(ResultInfo.INFO_ALREADY_CHILD);
        }
        sysMenu.setCreateUserId(currentUser.getId());
        if(save(sysMenu)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Transactional
    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysMenu org = getById(id);
        if (org == null){
            return res.data(false).reason("已不存在id为" + id + "的信息");
        }
        if (org.getId().equals(Const.ROOT_NODE_ID)||org.getParentId().equals(Const.ROOT_NODE_PARENTID)){
            return res.reason(ResultInfo.INFO_DEL_PARENT);
        }
        QueryWrapper<SysMenu> entity = new QueryWrapper<SysMenu>();
        entity.eq("parent_id",id);
        int children = baseMapper.selectCount(entity);
        if (children>0){
            return res.reason(ResultInfo.INFO_DEL_HASCHILDREN);
        }
        boolean flag = removeById(id);
        QueryWrapper<SysRoleMenu> rolemenu = new QueryWrapper<>();
        rolemenu.eq("menu_id",id);
        roleMenuService.remove(rolemenu);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res menuTree() {
        Res res = new Res();
        List<SysMenu> menus = baseMapper.selectList(null);
        List<BTree<SysMenu>> trees = new ArrayList<BTree<SysMenu>>();
        for (SysMenu org:menus){
            BTree<SysMenu> tree = new BTree<SysMenu>();
            tree.setId(String.valueOf(org.getId()));
            tree.setParentId(String.valueOf(org.getParentId()));
            tree.setTitle(org.getMenuName());
            trees.add(tree);
        }
        BTree<SysMenu> menuTree = TreeUtil.build(trees);
        return res.success().data(menuTree);
    }

    @Override
    public Res queryByRoleId(Long menuId) {
        Res res = new Res();
        QueryWrapper<SysRoleMenu> ew = new QueryWrapper<>();
        ew.eq("role_id",menuId);
        return res.success().data(sysRoleMenuMapper.selectList(ew));
    }

}

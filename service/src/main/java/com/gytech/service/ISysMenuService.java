package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> findByUserId(Long userid);

    Page query(Integer curPage,Integer pageSize,Map paramMap);

    Res add(SysMenu organization);

    Res del(Long id);

    Res menuTree();

    Res queryByRoleId(Long menuId);//

    Boolean childNameDump(String menuName,Long parentId,Long menuId);//统一父节点下 名称重复监测
}

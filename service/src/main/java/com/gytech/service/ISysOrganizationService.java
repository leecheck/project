package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Configuration.EnvContext;
import com.gytech.LocalEntity.BTree;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Security.entity.UserInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysOrganization;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行政区划 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysOrganizationService extends IService<SysOrganization> {

    Page query(Integer curPage,Integer pageSize,Map paramMap);

    Res add(SysOrganization organization);

    Res del(Long id);

    Res orgTree();

    Res orgTreeRest(UserInfo user);

    BTree<SysOrganization> cacheTree();

    /**
     * 用户session登录情况下取得权限下单位列表
     * @return
     */
    List<BTree> userOrgList();

    BTree<SysOrganization> updateTree();

    /**
     * 单位代码重复检测
     * @param organCode
     * @param organId
     * @return
     */
    Boolean organCodeDump(String organCode,Long organId);
}

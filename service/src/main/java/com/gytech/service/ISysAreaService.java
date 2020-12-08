package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.BTree;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
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
public interface ISysAreaService extends IService<SysArea> {
    Page query(Integer curPage,Integer pageSize,Map param);

    Res add(SysArea organization);

    Res del(Long id);

    Res orgTree();

    /**
     * 验证区域代码重复
     * @param areaCode
     * @param areaId
     * @return
     */
    Boolean areaCodeDump(String areaCode,Long areaId);

}

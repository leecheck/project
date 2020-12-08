package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gytech.entity.admin.SysOrganization;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysDictService extends IService<SysDict> {

    Page query(Integer curPage,Integer pageSize,Map param);

    Res add(SysDict sysDict);

    Res del(Long id);

    Res dictTree();

    /**
     * 根据dictcode查询字典子项
     * @param dictCode
     * @return
     */
    Res selectItemsByDictCode(String dictCode);

    /**
     * 根据dictcode查询字典项
     * @param dictCode
     * @return
     */
    Res selectDictByCode(String dictCode);

    Boolean dictCodeDump(String dictCode,Long dictId);//新增验证重名

}

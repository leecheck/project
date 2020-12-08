package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysStore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2018-12-03
 */
public interface ISysStoreService extends IService<SysStore> {

    boolean add(SysStore sysStore);

    Res backupStore(Long id);

    Res connect(Long id);

}

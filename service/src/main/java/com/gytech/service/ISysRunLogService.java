package com.gytech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysRunLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysRunLogService extends IService<SysRunLog> {

    IPage query(Integer curPage, Integer pageSize, Map paramMap);

    Res del(Long id);//删除
}

package com.gytech.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 字典项表 服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysLogService extends IService<SysLog> {

    IPage querySysLog(Integer curPage, Integer pageSize, Map paramMap);//多条件查

    Res addLog(SysLog sysLog);//新增日志

    Res del(Long id);//删除
}

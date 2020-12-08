package com.gytech.mapper.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.entity.admin.SysRunLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface SysRunLogMapper extends BaseMapper<SysRunLog> {
    List<Map> queryAllRunLog(Page page, Map param);
    int selectACount(Map param);
}

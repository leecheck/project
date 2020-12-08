package com.gytech.mapper.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.entity.admin.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典项表 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface SysLogMapper extends BaseMapper<SysLog> {
    List<Map> selectAll(Page page,Map param);
    int selectACount(Map param);
    int selectCount();
    int selectCount(Map param);
}

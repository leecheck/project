package com.gytech.mapper.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.entity.admin.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserList(RowBounds rowBounds, @Param("ew") QueryWrapper<SysUser> wrapper);
    List<Map> selectAllUser(Page page, Map param);
    int selectACount(Map param);
}

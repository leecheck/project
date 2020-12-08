package com.gytech.mapper.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.entity.admin.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> queryByUserId(Long userId);
}

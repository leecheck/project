package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysMaplayer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gytech.entity.admin.SysRole;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysMaplayerService extends IService<SysMaplayer> {


    Page query(Integer curPage,Integer pageSize,Map param);

    Res add(SysMaplayer sysMaplayer);

    Res del(Long id);

    Res getMapConfig();

}

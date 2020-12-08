package com.gytech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
public interface ISysFileService extends IService<SysFile> {
    Page query(Integer curPage,Integer pageSize,Map paramMap);

    Res del(Long id);// 删除

    Object downloadById(Long id, HttpServletResponse response);
}

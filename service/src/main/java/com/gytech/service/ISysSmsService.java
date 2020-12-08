package com.gytech.service;

import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysSms;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LQ
 * @since 2019-06-12
 */
public interface ISysSmsService extends IService<SysSms> {

    /**
     * 绑定模板发送对象userid
     * @param id
     * @param userIds
     * @return
     */
    Res bindUser(Long id, String userIds, Res res);

}

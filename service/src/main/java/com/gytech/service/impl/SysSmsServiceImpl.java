package com.gytech.service.impl;

import com.gytech.LocalEntity.Res;
import com.gytech.entity.admin.SysSms;
import com.gytech.mapper.admin.SysSmsMapper;
import com.gytech.service.ISysSmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2019-06-12
 */
@Service
public class SysSmsServiceImpl extends ServiceImpl<SysSmsMapper, SysSms> implements ISysSmsService {

    @Override
    public Res bindUser(Long id, String userIds,Res res) {
        SysSms query = new SysSms();
        SysSms sysSms = getById(id);
        sysSms.setSmsUser(userIds);
        baseMapper.updateById(sysSms);
        return res.success();
    }
}

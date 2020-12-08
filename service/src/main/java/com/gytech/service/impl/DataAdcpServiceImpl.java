package com.gytech.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.entity.work.DataAdcp;
import com.gytech.entity.work.StationMeteorology;
import com.gytech.mapper.work.DataAdcpMapper;
import com.gytech.service.IDataAdcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ADCP数据表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class DataAdcpServiceImpl extends ServiceImpl<DataAdcpMapper, DataAdcp> implements IDataAdcpService {

    @Autowired
    private DataAdcpMapper repo;

    @Override
    public List<DataAdcp> getByRelateID(Integer relateId) {
        LambdaQueryWrapper<DataAdcp> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DataAdcp::getAdcpStationId,relateId);
        wrapper.orderByAsc(DataAdcp::getObsDepth);
        return repo.selectList(wrapper);
    }
}

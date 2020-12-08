package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.StationAdcp;
import com.gytech.mapper.work.StationAdcpMapper;
import com.gytech.service.IStationAdcpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ADCP站位信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class StationAdcpServiceImpl extends ServiceImpl<StationAdcpMapper, StationAdcp> implements IStationAdcpService {

    @Autowired
    private StationAdcpMapper repo;
    @Override
    public List<StationAdcp> getByCruiseSegId(int cruiseId) {
        LambdaQueryWrapper<StationAdcp> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StationAdcp::getCruiseSegmentId,cruiseId);
        wrapper.orderByAsc(StationAdcp::getObsDate,StationAdcp::getObsTime);
        return repo.selectList(wrapper);
    }
}

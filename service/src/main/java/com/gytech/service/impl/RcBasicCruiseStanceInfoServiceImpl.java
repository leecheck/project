package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.RcBasicCruiseStanceInfo;
import com.gytech.mapper.work.RcBasicCruiseSegmentInfoMapper;
import com.gytech.mapper.work.RcBasicCruiseStanceInfoMapper;
import com.gytech.service.IRcBasicCruiseStanceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 站位信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class RcBasicCruiseStanceInfoServiceImpl extends ServiceImpl<RcBasicCruiseStanceInfoMapper, RcBasicCruiseStanceInfo> implements IRcBasicCruiseStanceInfoService {

    @Autowired
    private RcBasicCruiseStanceInfoMapper repo;

    @Override
    public List<RcBasicCruiseStanceInfo> getByCruiseSegId(int cruiseId) {
        LambdaQueryWrapper<RcBasicCruiseStanceInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RcBasicCruiseStanceInfo::getCruiseSegmentId,cruiseId);
        wrapper.orderByAsc(RcBasicCruiseStanceInfo::getArrStationTime);
        return repo.selectList(wrapper);
    }
}

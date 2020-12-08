package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.RcBasicCruiseSegmentInfo;
import com.gytech.mapper.work.RcBasicCruiseSegmentInfoMapper;
import com.gytech.service.IRcBasicCruiseSegmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 共享航次航段信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class RcBasicCruiseSegmentInfoServiceImpl extends ServiceImpl<RcBasicCruiseSegmentInfoMapper, RcBasicCruiseSegmentInfo> implements IRcBasicCruiseSegmentInfoService {

    @Autowired
    private RcBasicCruiseSegmentInfoMapper repo;
    @Override
    public List<RcBasicCruiseSegmentInfo> getByCruiseId(int cruiseId) {
        LambdaQueryWrapper<RcBasicCruiseSegmentInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RcBasicCruiseSegmentInfo::getCruiseId,cruiseId);
        wrapper.orderByAsc(RcBasicCruiseSegmentInfo::getStartDate);
        return repo.selectList(wrapper);
    }
}

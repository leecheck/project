package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.DataAdcp;
import com.gytech.entity.work.DataCtd;
import com.gytech.mapper.work.DataCtdMapper;
import com.gytech.service.IDataCtdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * CTD数据表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class DataCtdServiceImpl extends ServiceImpl<DataCtdMapper, DataCtd> implements IDataCtdService {

    @Autowired
    private DataCtdMapper repo;

    @Override
    public List<DataCtd> datasByStationAndSegment(String stationName, int segmentId) {
        LambdaQueryWrapper<DataCtd> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DataCtd::getCruiseStanceName,stationName);
        wrapper.eq(DataCtd::getCruiseSegmentId,segmentId);
        wrapper.orderByAsc(DataCtd::getObsDepth);
        return repo.selectList(wrapper);
    }
}

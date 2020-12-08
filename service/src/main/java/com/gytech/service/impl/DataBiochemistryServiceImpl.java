package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.DataAdcp;
import com.gytech.entity.work.DataBiochemistry;
import com.gytech.mapper.work.DataBiochemistryMapper;
import com.gytech.service.IDataBiochemistryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 海洋生物化学数据表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class DataBiochemistryServiceImpl extends ServiceImpl<DataBiochemistryMapper, DataBiochemistry> implements IDataBiochemistryService {

    @Autowired
    private DataBiochemistryMapper repo;

    @Override
    public List<DataBiochemistry> getByStationNameAndSegment(String stationName, Integer segmentId) {
        LambdaQueryWrapper<DataBiochemistry> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DataBiochemistry::getCruiseStanceName,stationName);
        wrapper.eq(DataBiochemistry::getCruiseSegmentId,segmentId);
        wrapper.orderByAsc(DataBiochemistry::getObsDepth);
        return repo.selectList(wrapper);
    }
}

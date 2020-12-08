package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.dto.work.CruiseRelateDto;
import com.gytech.entity.work.RcBasicCruiseInfo;
import com.gytech.entity.work.RcBasicCruiseProjectInfo;
import com.gytech.mapper.work.RcBasicCruiseInfoMapper;
import com.gytech.service.IRcBasicCruiseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 共享航次信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class RcBasicCruiseInfoServiceImpl extends ServiceImpl<RcBasicCruiseInfoMapper, RcBasicCruiseInfo> implements IRcBasicCruiseInfoService {

    @Autowired
    private RcBasicCruiseInfoMapper repo;

    @Autowired
    private RcBasicCruiseProjectInfoServiceImpl projectInfoService;


    @Override
    public CruiseRelateDto getByCruiseId(int cruiseId) {
        return null;
    }

    @Override
    public RcBasicCruiseInfo getByProjectId(Integer projectId) {
        LambdaQueryWrapper<RcBasicCruiseInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RcBasicCruiseInfo::getProjectInfoId,projectId);
        return repo.selectOne(wrapper);
    }
}

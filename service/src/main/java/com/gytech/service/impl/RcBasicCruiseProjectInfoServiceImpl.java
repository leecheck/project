package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.dto.work.CruiseRelateDto;
import com.gytech.entity.work.RcBasicCruiseInfo;
import com.gytech.entity.work.RcBasicCruiseProjectInfo;
import com.gytech.entity.work.RcBasicCruiseSegmentInfo;
import com.gytech.mapper.work.RcBasicCruiseInfoMapper;
import com.gytech.mapper.work.RcBasicCruiseProjectInfoMapper;
import com.gytech.service.IRcBasicCruiseInfoService;
import com.gytech.service.IRcBasicCruiseProjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.service.IRcBasicCruiseSegmentInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 共享航次项目信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class RcBasicCruiseProjectInfoServiceImpl extends ServiceImpl<RcBasicCruiseProjectInfoMapper, RcBasicCruiseProjectInfo> implements IRcBasicCruiseProjectInfoService {


    @Autowired
    private RcBasicCruiseProjectInfoMapper repo;

    @Autowired
    private IRcBasicCruiseInfoService cruiseInfoService;

    @Autowired
    private IRcBasicCruiseSegmentInfoService segmentInfoService;

    @Override
    public CruiseRelateDto getByPro(RcBasicCruiseProjectInfo projectInfo) {
        RcBasicCruiseInfo cruise = cruiseInfoService.getByProjectId(projectInfo.getId());
        List<RcBasicCruiseSegmentInfo> segs = new ArrayList<>();
        if (null != cruise){
            segs = segmentInfoService.getByCruiseId(cruise.getId());
        }
        return CruiseRelateDto.builder()
                .cruiseProjectInfo(projectInfo)
                .basicCruiseInfo(cruise)
                .cruiseSegmentInfos(segs).build();
    }


    @Override
    public List<CruiseRelateDto> getByCruiseLike(String year, String name) {
        LambdaQueryWrapper<RcBasicCruiseProjectInfo> proWrapper = Wrappers.lambdaQuery();
        proWrapper.like(RcBasicCruiseProjectInfo::getImplYear,year);
        proWrapper.like(RcBasicCruiseProjectInfo::getProjectName,name);
        List<RcBasicCruiseProjectInfo> pros = repo.selectList(proWrapper);
        List<CruiseRelateDto> list = new ArrayList<>();
        for (RcBasicCruiseProjectInfo pro : pros){
            list.add(getByPro(pro));
        }
        return list;
    }
}

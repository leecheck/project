package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.dto.work.MeteorologyDto;
import com.gytech.entity.work.DataMeteorology;
import com.gytech.entity.work.StationMeteorology;
import com.gytech.mapper.work.StationMeteorologyMapper;
import com.gytech.service.IStationMeteorologyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 海洋气象站位信息 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class StationMeteorologyServiceImpl extends ServiceImpl<StationMeteorologyMapper, StationMeteorology> implements IStationMeteorologyService {

    @Autowired
    private StationMeteorologyMapper repo;

    @Override
    public List<MeteorologyDto> spaceQuery(String extent, Integer thin, Integer segId, String where) {
        return repo.spaceQuery(extent,thin,segId,where);
    }

    @Override
    public List<StationMeteorology> baseQuery() {
        LambdaQueryWrapper<StationMeteorology> wrapper = Wrappers.lambdaQuery();
        wrapper.le(StationMeteorology::getId,17000);
        return repo.selectList(wrapper);
    }
}

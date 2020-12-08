package com.gytech.service;

import com.gytech.dto.work.MeteorologyDto;
import com.gytech.entity.work.StationMeteorology;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 海洋气象站位信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IStationMeteorologyService extends IService<StationMeteorology> {

    /**
     * 数量级上万 因此次做spaceQuery
     * @param extent
     * @param thin 抽稀指数（1/thin）
     * @param segId
     * @param where
     * @return
     */
    List<MeteorologyDto> spaceQuery(String extent,Integer thin,Integer segId,String where);

    List<StationMeteorology> baseQuery();

}

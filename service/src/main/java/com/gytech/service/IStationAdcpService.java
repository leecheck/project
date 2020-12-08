package com.gytech.service;

import com.gytech.entity.work.StationAdcp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * ADCP站位信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IStationAdcpService extends IService<StationAdcp> {

    /**
     * adcp站位集合 因为数量级在1000 全部返回了
     * @param cruiseId
     * @return
     */
    List<StationAdcp> getByCruiseSegId(int cruiseId);

}

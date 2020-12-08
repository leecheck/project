package com.gytech.service;

import com.gytech.entity.work.RcBasicCruiseStanceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 站位信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IRcBasicCruiseStanceInfoService extends IService<RcBasicCruiseStanceInfo> {

    /**
     * 航段站位集合
     * @param cruiseId
     * @return
     */
    List<RcBasicCruiseStanceInfo> getByCruiseSegId(int cruiseId);
}

package com.gytech.service;

import com.gytech.entity.work.DataBiochemistry;
import com.gytech.entity.work.DataCtd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * CTD数据表 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IDataCtdService extends IService<DataCtd> {


    /**
     * 获取站位ctd数据 观测水深正序
     * @param stationName stanceStationName
     * @param segmentId 航段id
     * @return
     */
    List<DataCtd> datasByStationAndSegment(String stationName, int segmentId);

}

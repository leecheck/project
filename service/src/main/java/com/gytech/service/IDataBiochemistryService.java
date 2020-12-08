package com.gytech.service;

import com.gytech.entity.work.DataBiochemistry;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 海洋生物化学数据表 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IDataBiochemistryService extends IService<DataBiochemistry> {

    /**
     * 获取站位生化数据 观测水深正序
     * @param stationName stanceStationName
     * @param segmentId 航段id
     * @return
     */
    List<DataBiochemistry> getByStationNameAndSegment(String stationName,Integer segmentId);

}

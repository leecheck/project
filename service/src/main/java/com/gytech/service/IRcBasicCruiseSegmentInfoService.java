package com.gytech.service;

import com.gytech.entity.work.RcBasicCruiseSegmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 共享航次航段信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IRcBasicCruiseSegmentInfoService extends IService<RcBasicCruiseSegmentInfo> {

    List<RcBasicCruiseSegmentInfo> getByCruiseId(int cruiseId);
}

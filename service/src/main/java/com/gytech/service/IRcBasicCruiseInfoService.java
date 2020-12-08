package com.gytech.service;

import com.gytech.dto.work.CruiseRelateDto;
import com.gytech.entity.work.RcBasicCruiseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gytech.entity.work.RcBasicCruiseProjectInfo;

import java.util.List;

/**
 * <p>
 * 共享航次信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IRcBasicCruiseInfoService extends IService<RcBasicCruiseInfo> {



    /**
     * id查询航次相关信息
     * @param cruiseId
     * @return
     */
    CruiseRelateDto getByCruiseId(int cruiseId);

    RcBasicCruiseInfo getByProjectId(Integer projectId);

}

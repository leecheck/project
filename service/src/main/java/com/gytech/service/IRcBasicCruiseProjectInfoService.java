package com.gytech.service;

import com.gytech.dto.work.CruiseRelateDto;
import com.gytech.entity.work.RcBasicCruiseProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 共享航次项目信息 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IRcBasicCruiseProjectInfoService extends IService<RcBasicCruiseProjectInfo> {

    CruiseRelateDto getByPro(RcBasicCruiseProjectInfo projectInfo);

    /**
     * 智能模糊查询
     * @param year 年度
     * @param name 名称
     * @return
     */
    List<CruiseRelateDto> getByCruiseLike(String year, String name);

}

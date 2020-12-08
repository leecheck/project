package com.gytech.service;

import com.gytech.entity.work.DataAdcp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * ADCP数据表 服务类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface IDataAdcpService extends IService<DataAdcp> {

    /**
     * 按station的metadataid查询对应的数据 按观测层深度正向排序
     * @param relateId
     * @return
     */
    List<DataAdcp> getByRelateID(Integer relateId);

}

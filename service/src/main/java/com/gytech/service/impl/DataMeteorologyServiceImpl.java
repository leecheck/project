package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gytech.entity.work.DataMeteorology;
import com.gytech.mapper.work.DataMeteorologyMapper;
import com.gytech.service.IDataMeteorologyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 海洋气象数据表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
@Service
public class DataMeteorologyServiceImpl extends ServiceImpl<DataMeteorologyMapper, DataMeteorology> implements IDataMeteorologyService {

    @Autowired
    private DataMeteorologyMapper repo;

}

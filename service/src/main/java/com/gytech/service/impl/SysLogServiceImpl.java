package com.gytech.service.impl;


import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gytech.entity.admin.SysRunLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysLog;
import com.gytech.mapper.admin.SysLogMapper;
import com.gytech.service.ISysLogService;

/**
 * <p>
 * 字典项表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
@Autowired
private SysLogMapper sysLogMapper;

    public IPage querySysLog(Integer curPage, Integer pageSize, Map paramMap) {
        Page pageEntity = new Page<SysLog>(curPage,pageSize);
        QueryWrapper<SysLog> wrapper = new QueryWrapper();
        Integer userId=GU.getMapKeyInt("userId",paramMap);
        String logType=GU.getMapKeyString("logType",paramMap);
        if (StringUtils.isNotBlank(logType)){
            wrapper.eq("log_type", logType);
        }
        if (userId>0){
            wrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(paramMap.get("createTime").toString())) {
            wrapper.ge("create_time",paramMap.get("createTime"));
        }
        if (StringUtils.isNotBlank(paramMap.get("overTime").toString())) {
            wrapper.le("over_time",paramMap.get("overTime"));
        }
        return page(pageEntity,wrapper);
    }
    public Res addLog(SysLog sysLog) {
        Res res=new Res();
        if(sysLog==null){
            return res.reason(ResultInfo.INFO_NULLPOINTER);
        }
        if(save(sysLog)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    public Res del(Long id) {
        Res res = new Res();
        SysLog sysLog = getById(id);
        if (sysLog == null){
            return res.data(false).reason("已不存在id为" + id + "的信息`");
        }
        boolean flag = removeById(id);
        if (flag){
            return res.data(true).success();
        }
        if (updateById(sysLog)){
            return res.success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }
}

package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysRunLog;
import com.gytech.mapper.admin.SysRunLogMapper;
import com.gytech.service.ISysRunLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

import static java.sql.JDBCType.NULL;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysRunLogServiceImpl extends ServiceImpl<SysRunLogMapper, SysRunLog> implements ISysRunLogService {

    @Autowired
    private SysRunLogMapper sysRunLogMapper;

    @PostConstruct
    private void insertStartLog(){
        SysRunLog sysRunLog = new SysRunLog();
        sysRunLog.setActionStart(new Date());
        sysRunLog.setCreateTime(new Date());
        sysRunLog.setActionLog("系统启动");
        sysRunLog.setActionDesc("系统启动");
        save(sysRunLog);
    }

    @Override
    public IPage query(Integer curPage, Integer pageSize, Map paramMap) {
        Page pageEntity = new Page<SysRunLog>(curPage, pageSize);
        QueryWrapper<SysRunLog> wrapper = new QueryWrapper();
        String name = GU.getMapKeyString("userName", paramMap);
        Object tbStartTime = paramMap.get("actionStart");
        Object tbOverTime = paramMap.get("actionEnd");
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("user_name", name);
        }
        if (StringUtils.isNotBlank(tbStartTime.toString())) {
            wrapper.ge("action_start",tbStartTime);
        }
        if (StringUtils.isNotBlank(tbOverTime.toString())) {
            wrapper.le("action_end",tbOverTime);
        }
        return page(pageEntity,wrapper);
    }

    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysRunLog sysRunLog = getById(id);
        if (sysRunLog == null) {
            return res.data(false).reason("已不存在id为" + id + "的信息`");
        }
        boolean flag = removeById(id);
        if (flag) {
            return res.data(true).success();
        }
        if (updateById(sysRunLog)) {
            return res.success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }
}

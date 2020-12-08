package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.Configuration.ConfigManager;
import com.gytech.Configuration.EnvContext;
import com.gytech.Const;
import com.gytech.LocalEntity.LayerGroup;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.LocalEntity.SysConf;
import com.gytech.Utils.GU;
import com.gytech.entity.admin.SysDict;
import com.gytech.entity.admin.SysMaplayer;
import com.gytech.entity.admin.SysRole;
import com.gytech.entity.admin.SysUser;
import com.gytech.mapper.admin.SysDictMapper;
import com.gytech.mapper.admin.SysMaplayerMapper;
import com.gytech.service.ISysDictService;
import com.gytech.service.ISysMaplayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LQ
 * @since 2018-09-19
 */
@Service
public class SysMaplayerServiceImpl extends ServiceImpl<SysMaplayerMapper, SysMaplayer> implements ISysMaplayerService {

    @Autowired
    private EnvContext envContext;

    @Autowired
    private SysMaplayerMapper maplayerMapper;

    @Autowired
    private ISysDictService dictService;

    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysMaplayer>(curPage,pageSize);
        String layerGroup = GU.getMapKeyString("layerGroup",paramMap);
        String layerType = GU.getMapKeyString("layerType",paramMap);
        QueryWrapper<SysMaplayer> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(layerGroup)){
            wrapper.eq("layer_group",layerGroup);
        }
        if (StringUtils.isNotBlank(layerType)){
            wrapper.eq("layer_type",layerType);
        }
        pageEntity.setRecords(maplayerMapper.selectList(wrapper));
        return pageEntity;
    }

    @Override
    public Res add(SysMaplayer sysMaplayer) {
        Res res = new Res();
        SysUser currentUser = envContext.getCurrentUser();
        if (currentUser==null){
            return res.reason(ResultInfo.INFO_NOT_LOGIN);
        }
        QueryWrapper<SysMaplayer> entity = new QueryWrapper<SysMaplayer>();
        entity.eq("layer_id",sysMaplayer.getLayerId());
        if (baseMapper.selectCount(entity)>0){
            return res.reason("图层id已存在，修改后提交");
        }
        if (save(sysMaplayer)){
            return res.success();
        }
        return res.reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysMaplayer sysMaplayer = getById(id);
        if (sysMaplayer == null){
            return res.data(false).reason("已不存在id为" + id + "的角色");
        }
        boolean flag = removeById(id);
        if (flag){
            return res.data(true).success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Res getMapConfig() {
        Res res = new Res();
        Res mapGroupRes = dictService.selectItemsByDictCode(Const.LAYER_GROUP);
        if (mapGroupRes.error()){
            return res.reason("图层组查询失败，系统配置错误");
        }
        List<SysDict> mapGroups = (List<SysDict>) mapGroupRes.getData();
        Map<String,Object> mapConfig = new HashMap<>();
        for (SysDict group:mapGroups){
            QueryWrapper<SysMaplayer> entity = new QueryWrapper<SysMaplayer>();
            entity.eq("layer_group",group.getDictCode());
            List<SysMaplayer> sysMaplayers = maplayerMapper.selectList(entity);
            mapConfig.put(group.getDictCode(),sysMaplayers);
        }
        ConfigManager config = ConfigManager.getInstance();
        mapConfig.put("wkid",config.getWkid());
        return res.success().data(mapConfig);
    }

}

package com.gytech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.FileUtil;
import com.gytech.Utils.GU;
import com.gytech.Utils.UUID;
import com.gytech.entity.admin.SysFile;
import com.gytech.mapper.admin.SysFileMapper;
import com.gytech.service.ISysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;
    @Override
    public Page query(Integer curPage,Integer pageSize,Map paramMap) {
        Page pageEntity = new Page<SysFile>(curPage,pageSize);
        String fileType =  GU.getMapKeyString("type",paramMap);
        String fileName =  GU.getMapKeyString("name",paramMap);
        QueryWrapper<SysFile> wrapper = new QueryWrapper<SysFile>();
        if (StringUtils.isNotBlank(fileType)){
            wrapper.eq("type",fileType);
        }
        if (StringUtils.isNotBlank(fileName)){
            wrapper.eq("name",fileName);
        }
        pageEntity.setRecords(sysFileMapper.selectList(wrapper));
        return pageEntity;
    }

    @Override
    public Res del(Long id) {
        Res res = new Res();
        SysFile sysFile=getById(id);
        if (sysFile == null){
            return res.data(false).reason("已不存在id为" + id + "的信息");
        }
        sysFile.setIsDelete(1);
        if(updateById(sysFile)){
            return res.success();
        }
        return res.data(false).reason(ResultInfo.INFO_SQL_RETRY);
    }

    @Override
    public Object downloadById(Long id,HttpServletResponse response) {
        Res res = new Res();
        SysFile sysFile = getById(id);
        String path = sysFile.getPath();
        File file = new File(path);
        if (!file.exists()){
            return res.reason("错误:该地址下文件不存在");
        }
        try {
            FileUtil.sendFile(file, response);
        } catch (IOException e) {
            e.printStackTrace();
            return res.reason("该地址下文件读写错误");
        }
        return res.success();
    }

}

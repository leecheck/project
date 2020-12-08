package com.gytech.Configuration;

/**
 * Created by LQ on 2018/9/25.
 * com.gytech.Configuration
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/** mybatisplus自定义填充公共字段 ,即没有传的字段自动填充*/
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object insertTime = getFieldValByName("createTime", metaObject);
        if (null == insertTime) {
            setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object lastUpdateTime = getFieldValByName("updateTime", metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}

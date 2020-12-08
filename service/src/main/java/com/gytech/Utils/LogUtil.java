package com.gytech.Utils;

import com.gytech.Const;
import com.gytech.entity.admin.SysLog;
import com.gytech.entity.admin.SysUser;

import java.util.Date;

/**
 * Created by LQ on 2018/12/4.
 * com.gytech.Utils
 */
public class LogUtil {

    public final static String LOG_TYPE_INSERT = "insert";
    public final static String LOG_TYPE_UPDATE = "update";
    public final static String LOG_TYPE_DELETE = "delete";
    public final static String LOG_TYPE_SQLBACKUP = "backup";



    public static SysLog createLogByEvent(String event, String object, String detail, SysUser sysUser) {
        SysLog sysLog = new SysLog();
        sysLog.setLogType(Const.LOG_TYPE_ADMINLOG);
        sysLog.setCreateTime(new Date());
        String userName = "";
        if (sysUser != null){
            userName = sysUser.getDisplayName();
            sysLog.setUserId(sysUser.getId());
        }
        String eventInfo = userName + " ";
        switch (event){
            case LOG_TYPE_INSERT:
              eventInfo += "对 " + object + " 进行了新增操作";
              break;
            case LOG_TYPE_UPDATE:
                eventInfo += "对 " + object + " 进行了更新操作";
                break;
            case LOG_TYPE_DELETE:
                eventInfo += "对 " + object + " 进行了删除操作";
                break;
            case LOG_TYPE_SQLBACKUP:
                eventInfo += "对 " + object + " 进行了数据库备份操作";
                break;
            default:
                break;
        }
        sysLog.setEvent(eventInfo);
        sysLog.setDetail(detail);
        return sysLog;
    }

    public static SysLog failedLog(String evevt, String object, String detail, SysUser sysUser) {
        SysLog sysLog = new SysLog();
        sysLog.setLogType(Const.LOG_TYPE_ADMINLOG);
        sysLog.setCreateTime(new Date());
        String userName = "";
        if (sysUser != null){
            userName = sysUser.getDisplayName();
            sysLog.setUserId(sysUser.getId());
        }
        String eventInfo = userName + " ";
        switch (evevt){
            case LOG_TYPE_INSERT:
                eventInfo += "对 " + object + " 新增操作失败";
                break;
            case LOG_TYPE_UPDATE:
                eventInfo += "对 " + object + " 更新操作失败";
                break;
            case LOG_TYPE_DELETE:
                eventInfo += "对 " + object + " 删除操作失败";
                break;
            case LOG_TYPE_SQLBACKUP:
                eventInfo += "对 " + object + " 数据库备份操作失败";
                break;
            default:
                break;
        }
        sysLog.setEvent(eventInfo);
        sysLog.setDetail(detail);
        return sysLog;
    }
}

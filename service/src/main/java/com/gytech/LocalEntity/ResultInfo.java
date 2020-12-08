package com.gytech.LocalEntity;

/**
 * Created by LQ on 2018/9/25.
 * com.gytech.LocalEntity
 */
public final class ResultInfo {

    public final static String INFO_NULLPOINTER = "空指针异常，请联系开发人员";

    public final static String INFO_SQL_RETRY = "数据库操作失败，请稍后重试";

    public final static String INFO_NOT_LOGIN = "未获得登录信息，请重新登录";

    public final static String INFO_NOT_FIND = "当前选中记录已不存在，请刷新数据";

    public final static String INFO_PARAM_DECODE_ERROR = "请求参数转码失败";

    public final static String INFO_PARAM_MAP_ERROR = "请求参数无法解析为MAP数据";

    public final static String INFO_ADD_AREADYPARENT = "不可重复添加根节点";

    public final static String INFO_ADD_NOPARENT = "不可重复添加根节点";

    public final static String INFO_ADD_ATTALREADY = "属性重复，请修改后提交";

    public final static String INFO_DEL_PARENT = "不可删除根节点";

    public final static String INFO_DEL_HASCHILDREN = "当前节点存在子节点，不可删除";

    public final static String INFO_ALREADY_CHILD = "同级节点存在重复";

    public final static String INFO_UNSUPPORTED = "不支持的类型";

    public final static Integer ERROR_TOKENINVALID = 510;
    public final static String INFO_TOKENINVALID = "非法的Token";

    public final static Integer ERROR_TOKENNULL = 511;
    public final static String INFO_TOKENNULL = "需要授权的接口请求！未携带Token";

}

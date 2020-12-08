package com.gytech;

import java.nio.charset.Charset;

/**
 * Created by LQ on 2018/9/14.
 * com.gytech
 */
public final class Const {

    public final static String UTF_8_ENCODING = "UTF-8";

    public final static String DEFAULT_ENCODING = UTF_8_ENCODING;

    public final static Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_ENCODING);

    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final static String DATE_FORMAT = "yyyy-MM-dd";

    public final static String TIME_FORMAT = "HH:mm:ss";

    public final static Long ROOT_NODE_ID = 1L;
    public final static Long ROOT_NODE_PARENTID = 0L;

    public final static String ROLE_ADMIN = "ROLE_ADMIN";

    public final static String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    public final static String ROLE_USER = "ROLE_USER";

    public final static String POWER_ADMIN = "POWER_ADMIN";
    public final static String POWER_SUADMIN = "POWER_SUADMIN";//超管权限
    public final static String POWER_TASKEDIT = "POWER_TASKEDIT";


    public final static String LAYER_GROUP = "layerGroup";
    public final static String LAYER_TYPE = "layerType";
    public final static String LOG_TYPE = "logType";
    public final static String LOG_TYPE_ADMINLOG = "adminLog";

    public final static String FILE_TYPE_SQLBACKUP = "sql";

    public final static String MYSQL = "mysql";
    public final static String ORACLE = "oracle";


    public final static String AES_KEY = "ltmap";


    public final static String CACHE_ORG = "CACHE_ORG";





    
    /**
     * 任务异常类型
     *
     */
    public enum TaskExp{
    	EXP_NONE("正常"),EXP_EXE("任务操作异常"),EXP_DRONE("发现无人机"),EXP_EXCEED("频谱超限");
    	private String description;
    	private TaskExp(String description) {
    		this.description = description;
    	}
    	
    	public String getDescription() {
			return description;
		}
    	
    	public static String matchRegex() {
    		return "";
    	}
    }
    
}

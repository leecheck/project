package com.gytech.Utils;
import com.gytech.entity.admin.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 常用的一些工具方法
 * Created by LQ on 2018/7/11.
 */
public class GU {

    /**
     * 通过判断简化map中字符串的操作 "null"会转为""
     * @param key
     * @param map
     * @return
     */
    public static String getMapKeyString(String key, Map map){
        if (map!=null){
            if (map.containsKey(key)){
                String value = String.valueOf(map.get(key));
                if (value.equalsIgnoreCase("null")){
                    return "";
                }
                return value;
            }
            return "";
        }
        return "";
    }

    /***
     * 运行日志
     * @return
     */
    public static SysLog addLog(Long userId,String key,String logStr){
        SysLog sysLog=new SysLog(userId,key,logStr);
        return sysLog;
    }
    /**
     * 通过判断简化map中字符串的操作 "null"会转为""
     * @param key
     * @param map
     * @return
     */
    public static int getMapKeyInt(String key, Map map){
        String originInt = getMapKeyString(key, map);
        return string2Int(originInt);
    }

    public static Long getMapKeyLong(String key, Map map){
        String originLong = getMapKeyString(key, map);
        if (originLong.equalsIgnoreCase("")){
            return 0L;
        }
        Long num;
        try {
            num = Long.valueOf(originLong);
        } catch (NumberFormatException e) {
            num = 1L;
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 通过判断简化map中字符串的操作 "null"会转为""
     * @param key
     * @param map
     * @return
     */
    public static Double getMapKeyDou(String key, Map map){
        String origin = getMapKeyString(key, map);
        return string2Dou(origin);
    }

    public static Float getMapKeyFloat(String key, Map map){
        String origin = getMapKeyString(key, map);
        return string2Float(origin);
    }

    public static Double formatMinDou(String keymin,String keymax, Map map){
        double min = getMapKeyDou(keymin,map);
        double max = getMapKeyDou(keymax,map);
        min = min < max? min : max;
        return min;
    }

    public static Double formatMaxDou(String keymin,String keymax, Map map){
        double min = getMapKeyDou(keymin,map);
        double max = getMapKeyDou(keymax,map);
        max = min < max? max : min;
        return max;
    }

    public static boolean getMapKeyboolean(String key, Map map){
        String origin = getMapKeyString(key, map);
        return string2boolean(origin);
    }

    /**
     *
     * @param value
     * @return
     */
    public static Double string2Dou(String value){
        if (strVoid(value)){
            return 0.0;
        }
        Double num;
        try {
            num = Double.valueOf(value);
        } catch (NumberFormatException e) {
            num = 0.0;
            e.printStackTrace();
        }
        return num;
    }

    public static Float string2Float(String value){
        if (strVoid(value)){
            return 0.0f;
        }
        Float num;
        try {
            num = Float.valueOf(value);
        } catch (NumberFormatException e) {
            num = 0.0f;
            e.printStackTrace();
        }
        return num;
    }

    public static Integer string2Int(String value){
        if (strVoid(value)){
            return 0;
        }
        int num;
        try {
            num = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            num = 1;
            e.printStackTrace();
        }
        return num;
    }

    public static boolean string2boolean(String value){
        if (strVoid(value)){
            return false;
        }
        boolean b;
        try {
            b = Boolean.valueOf(value);
        } catch (NumberFormatException e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }


    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     *
     * @return 返回短时间字符串格式yyyy-MM-dd-hh
     */
    public static String stringDateCode(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH");
        return formatter.format(date);
    }

    /**
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String dateYMD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateSeconds() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * string转日期
     * @param date
     * @param date
     * @return
     */
    public static Date string2Date(String date) {
        Date retValue = null ;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            retValue =sdf.parse(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return retValue;
    }

    /***
     * 日期转String
     * @param dateDate
     * @return
     */
    public static String getDate(Date dateDate,Map map) {
        String dateString="";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateString= formatter.format(dateDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringData() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 判断str null/"null"/"" 都返回true
     * @param obj
     * @return
     */
    public static boolean strVoid(Object obj){
        if (obj == null || String.valueOf(obj).equalsIgnoreCase("") ||String.valueOf(obj).equalsIgnoreCase("null")){
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static double format(double dou,int num){
        BigDecimal bg = new BigDecimal(dou);
        return bg.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String formatDouble(double dou,int num){
        return String.format("%." + num + "2f", dou);
    }

    /**
     * key比较混乱的时候 增加大小写去除_进行取值判断 如取thingId，则thingID、thing_id等都会被尝试
     * @param rowkey
     * @param map
     * @return
     */
    public static Object extractMapValue(String rowkey,Map<String,Object> map){
        if (map.containsKey(rowkey)){
            return map.get(rowkey);
        }
        if (map.containsKey(rowkey.replace("_", ""))){
            return map.get(rowkey);
        }
        if (map.containsKey(rowkey.toLowerCase())){
            return map.get(rowkey.toLowerCase());
        }
        if (map.containsKey(rowkey.toUpperCase())){
            return map.get(rowkey.toUpperCase());
        }
        for(String key: map.keySet()){
            String newKey = key.replace("_", "");
            String newRowKey = rowkey.replace("_", "");
            if (newKey.equalsIgnoreCase(newRowKey)){
                return map.get(key);
            }
        }
        return null;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date,String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(java.util.Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static boolean isAjax(HttpServletRequest httpRequest){
        String requestType = httpRequest.getHeader("X-Requested-With");
        if (requestType!=null&&requestType.equalsIgnoreCase("XMLHttpRequest")){
            return true;
        }
        return false;
    }
}

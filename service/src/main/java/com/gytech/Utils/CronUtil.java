package com.gytech.Utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.gytech.LocalEntity.CronRes;
import com.gytech.LocalEntity.Res;
import com.gytech.LocalEntity.StartStop;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 只支持月、日、时表达
 * Created by LQ on 2019/9/17.
 * com.gytech.Utils
 */
public class CronUtil {

    static final String xing ="*";
    static final String wenhao="?";
    static final String dao="-";
    static final String mei="/";
    static final String huo=",";

    public static CronRes parseCron(String cronExp){
        CronRes cronRes = new CronRes();
        if(CronSequenceGenerator.isValidExpression(cronExp)){
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cronExp);
            Date nextTimePoint1 = cronSequenceGenerator.next(currentTime);
            Date NextTimePoint2 = cronSequenceGenerator.next(nextTimePoint1);
            Date NextTimePoint3 = cronSequenceGenerator.next(NextTimePoint2);
            List next = new ArrayList();
            next.add(sdf.format(nextTimePoint1));
            next.add(sdf.format(NextTimePoint2));
            next.add(sdf.format(NextTimePoint3));
            cronRes.setNextTime(next);
            cronRes.setSuccess(true);
            cronRes.setData(descCorn(cronExp));
            return cronRes;
        }
        cronRes.setReason("不是合规的任务时间格式");
        return cronRes;
    }



    public static StartStop fromPeriod(Date start, Integer p){
        Long period = p * 60 * 60 * 1000L;
        StartStop startStop = new StartStop();
        Long startTime = start.getTime();
        Long startLong = 0L;
        Long endLong = 0L;
        if (period>=0){
            startLong = startTime ;
            endLong = startTime + period;
        }else {
            startLong = startTime;
            endLong = startTime - period;
        }
        startStop.setStartTime(new Date(startLong));
        startStop.setStopTime(new Date(endLong));
        return startStop;
    }

    public static String descCorn(String cronExp) {
        String[] tmpCorns = cronExp.split(" ");
        StringBuffer sBuffer = new StringBuffer();
        if (tmpCorns.length < 6 || tmpCorns.length > 7) {
            throw new RuntimeException("请补全表达式,必须标准的cron表达式才能解析");
        }

        if (tmpCorns.length == 6){
            descYear("*", sBuffer);
        }else {
            descYear(tmpCorns[6], sBuffer);
        }

        // 解析周
        descWeek(tmpCorns[5], sBuffer);

        // 解析月
        descMonth(tmpCorns[4], sBuffer);

        // 解析日
        descDay(tmpCorns[3], sBuffer);

        // 解析时
        descHour(tmpCorns[2], sBuffer);

        // 解析分
        descMintue(tmpCorns[1], sBuffer);

        // 解析秒
        descSecond(tmpCorns[0], sBuffer);
        sBuffer.append(" 执行");
        return sBuffer.toString();

    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:01:09
     */
    private static void descSecond(String s, StringBuffer sBuffer) {
        String danwei="秒";
        desc(s, sBuffer, danwei);
    }

    /**
     * 描述
     * @param s
     * @param sBuffer
     * @param danwei
     * @author Norton Lai
     * @created 2019-2-27 下午5:16:19
     */
        private static void desc(String s, StringBuffer sBuffer, String danwei) {
        if (s.equals("1/1")) {
            s="*";
        }
        if (s.equals("0/0")) {
            s="0";
        }
        if (xing.equals(s)) {
            sBuffer.append("每"+danwei);
            return;
        }
        if (wenhao.equals(s)) {
            return ;
        }
        if (s.contains(huo)) {
            String[] arr = s.split(huo);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length()!=0) {
                    sBuffer.append("第"+arr[i]+danwei+"和");
                }
            }
            sBuffer.deleteCharAt(sBuffer.length()-1);
            sBuffer.append("的");
            return;
        }
        if (s.contains(dao)) {
            String[] arr = s.split(dao);
            if (arr.length!=2) {
                throw new RuntimeException("表达式错误"+s);
            }
            sBuffer.append("从第"+arr[0]+danwei+"到第"+arr[1]+danwei+"每"+danwei);
            sBuffer.append("的");
            return;
        }

        if (s.contains(mei)) {
            String[] arr = s.split(mei);
            if (arr.length!=2) {
                throw new RuntimeException("表达式错误"+s);
            }
            if (arr[0].equals(arr[1])||arr[0].equals("0")) {
                sBuffer.append("每"+arr[1]+danwei);
            }else {
                sBuffer.append("每"+arr[1]+danwei+"的第"+arr[0]+danwei);
            }
            return;
        }
        sBuffer.append("第"+s+danwei);
    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:01:00
     */
    private static void descMintue(String s, StringBuffer sBuffer) {
        desc(s, sBuffer, "分钟");
    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:00:50
     */
    private static void descHour(String s, StringBuffer sBuffer) {
        desc(s, sBuffer, "小时");
    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:00:39
     */
    private static void descDay(String s, StringBuffer sBuffer) {
        desc(s, sBuffer, "天");
    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:00:39
     */
    private static void descYear(String s, StringBuffer sBuffer) {
        desc(s, sBuffer, "年");
    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:00:30
     */
    private static void descWeek(String s, StringBuffer sBuffer) {
        if(!s.equals(xing) && !s.equals(wenhao))
        {
            char[] tmpArray =  s.toCharArray();
            for(char tmp:tmpArray)
            {
                switch (tmp)
                {
                    case '1':
                        sBuffer.append("星期天");
                        break;
                    case '2':
                        sBuffer.append("星期一");
                        break;
                    case '3':
                        sBuffer.append("星期二");
                        break;
                    case '4':
                        sBuffer.append("星期三");
                        break;
                    case '5':
                        sBuffer.append("星期四");
                        break;
                    case '6':
                        sBuffer.append("星期五");
                        break;
                    case '7':
                        sBuffer.append("星期六");
                        break;
                    case '-':
                        sBuffer.append("至");
                        break;
                    default:
                        sBuffer.append(tmp);
                        break;
                }
            }
        }
    }
//    private static String turnWeek(String week){
//        switch (week) {
//        case "1":
//            return"星期天";
//        case "2":
//            return"星期一";
//        case "3":
//            return"星期二";
//        case "4":
//            return"星期三";
//        case "5":
//            return"星期四";
//        case "6":
//            return"星期五";
//        case "7":
//            return"星期六";
//        default:
//            return null;
//        }
//    }

    /**
     * 描述
     * @param sBuffer
     * @author Norton Lai
     * @created 2019-2-27 下午5:00:15
     */
    private static void descMonth(String s, StringBuffer sBuffer) {
        desc(s, sBuffer, "月");
    }

    // 测试方法
    public static void main(String[] args) {
//        String CRON_EXPRESSION = "0 0 0/2 * * ?";
        String CRON_EXPRESSION = "0 0 7 L 3,6,9,12 *";
//        String CRON_EXPRESSION = "0 0 5-8 * * ?";

        System.out.println(descCorn(CRON_EXPRESSION));
        CronSequenceGenerator.isValidExpression(CRON_EXPRESSION);
        System.out.println(CronSequenceGenerator.isValidExpression(CRON_EXPRESSION));
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(CRON_EXPRESSION);
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("currentTime: " + sdf.format(currentTime));
        Date nextTimePoint = cronSequenceGenerator.next(currentTime); // currentTime为计算下次时间点的开始时间
        System.out.println("nextTimePoint: " + sdf.format(nextTimePoint));
        Date nextNextTimePoint = cronSequenceGenerator.next(nextTimePoint);
        System.out.println("nextNextTimePoint: " + sdf.format(nextNextTimePoint));
        System.out.println(descCorn(CRON_EXPRESSION));
    }



}

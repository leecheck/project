package com.gytech.vo;

import java.io.Serializable;

/**
 * 潮汐
 *
 * @author Lucky Luffy
 * @date 2019/11/5
 */
public class TideVo implements Serializable {

    /**
     * 时间
     */
    private String forcastTime;

    /**
     * 潮汐值
     */
    private String tideData;

    public String getForcastTime() {
        return forcastTime;
    }

    public void setForcastTime(String forcastTime) {
        this.forcastTime = forcastTime;
    }

    public String getTideData() {
        return tideData;
    }

    public void setTideData(String tideData) {
        this.tideData = tideData;
    }
}

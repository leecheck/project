package com.gytech.vo;

import java.io.Serializable;

/**
 * 	获取监控点预览流URL返回实体
 * @author Lenovo
 *
 */
public class GetMonitorStreamResultData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2528231849748379437L;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}

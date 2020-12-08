package com.gytech.vo;

import java.io.Serializable;

/**
 * 获取监控点流URL请求实体
 * @author limap
 *
 */
public class GetMonitorStreamRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6856459137677893510L;
	private String cameraIndexCode;
	private	Integer streamType;
	private String protocol;
	private Integer transmode;
	private String expand;
	public String getCameraIndexCode() {
		return cameraIndexCode;
	}
	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}
	public Integer getStreamType() {
		return streamType;
	}
	public void setStreamType(Integer streamType) {
		this.streamType = streamType;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Integer getTransmode() {
		return transmode;
	}
	public void setTransmode(Integer transmode) {
		this.transmode = transmode;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	
}

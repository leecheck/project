package com.gytech.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author limap
 * 	事件订阅平台回调rest接口参数
 *
 */
public class EventCallbackParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4125919108986087213L;
	private String method;
	private Params params;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public static class Params implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7692185065153811800L;
		private String sendTime;
		private String ability;
		private List<Events> events = Lists.newArrayList();
		public String getSendTime() {
			return sendTime;
		}
		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}
		public String getAbility() {
			return ability;
		}
		public void setAbility(String ability) {
			this.ability = ability;
		}
		public List<Events> getEvents() {
			return events;
		}
		public void setEvents(List<Events> events) {
			this.events = events;
		}
		
	}
	
	public static class Events implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8631000478702519345L;
		private String eventId;//事件唯一标识
		private String srcIndex;//事件源编号，物理设备是资源编号
		private String srcType;//事件源类型	
		private String srcName;//事件源名称	
		private Long eventType;//事件类型	
		private Long status;//事件状态	
		private Long eventLvl;//0-未配置 1-低 2-中 3-高
		private Long timeout;//脉冲超时时间	
		private String happenTime;//事件发生时间（设备时间）		
		private String srcParentIdex;//事件发生的事件源父设备，无-空字符串	
		private String data;//事件其它扩展信息	
		public String getEventId() {
			return eventId;
		}
		public void setEventId(String eventId) {
			this.eventId = eventId;
		}
		public String getSrcIndex() {
			return srcIndex;
		}
		public void setSrcIndex(String srcIndex) {
			this.srcIndex = srcIndex;
		}
		public String getSrcType() {
			return srcType;
		}
		public void setSrcType(String srcType) {
			this.srcType = srcType;
		}
		public String getSrcName() {
			return srcName;
		}
		public void setSrcName(String srcName) {
			this.srcName = srcName;
		}
		public Long getEventType() {
			return eventType;
		}
		public void setEventType(Long eventType) {
			this.eventType = eventType;
		}
		public Long getStatus() {
			return status;
		}
		public void setStatus(Long status) {
			this.status = status;
		}
		public Long getEventLvl() {
			return eventLvl;
		}
		public void setEventLvl(Long eventLvl) {
			this.eventLvl = eventLvl;
		}
		public Long getTimeout() {
			return timeout;
		}
		public void setTimeout(Long timeout) {
			this.timeout = timeout;
		}
		public String getHappenTime() {
			return happenTime;
		}
		public void setHappenTime(String happenTime) {
			this.happenTime = happenTime;
		}
		public String getSrcParentIdex() {
			return srcParentIdex;
		}
		public void setSrcParentIdex(String srcParentIdex) {
			this.srcParentIdex = srcParentIdex;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		
		
	}
	

}

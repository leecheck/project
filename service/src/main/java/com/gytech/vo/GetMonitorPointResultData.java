package com.gytech.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.collect.Lists;

/**
 * 获取监控点列表返回实体
 * 
 * @author limap
 *
 */
@JSONType(includes = {"list"})
public class GetMonitorPointResultData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4182719733089985215L;
	private Integer total;
	private Integer pageSize;
	private Integer pageNo;
	private List<Node> list = Lists.newArrayList();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<Node> getList() {
		return list;
	}

	public void setList(List<Node> list) {
		this.list = list;
	}

	@JSONType(includes = {"cameraIndexCode","longitude","latitude","altitude","status","statusName","cameraName","cameraTypeName","channelTypeName","ptz","ptzName","ptzControllerName","pixel"})
	public static class Node implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3808112077298345715L;
		private String cameraIndexCode;
		private String gbIndexCode;
		private String cameraName;
		private String deviceIndexCode;
		private String longitude;
		private String latitude;
		private String altitude;
		private String pixel;
		private String cameraType;
		private String cameraTypeName;
		private String installPlace;
		private String matrixCode;
		private String channelNo;
		private String viewshed;
		private String capabilitySet;
		private String capabilitySetName;
		private String intelligentSet;
		private String intelligentSetName;
		private String recordLocation;
		private String recordLocationName;
		private String ptz;
		private String ptzName;
		private String ptzController;
		private String ptzControllerName;
		private String deviceResourceType;
		private String deviceResourceTypeName;
		private String channelType;
		private String channelTypeName;
		private String transType;
		private String transTypeName;
		private String updateTime;
		private String unitIndexCode;
		private String treatyType;
		private String treatyTypeName;
		private String createTime;
		private String status;
		private String statusName;
		private String encodeDevIndexCode;
		private String encodeDevResourceType;
		private String encodeDevResourceTypeName;
		private String installLocation;
		private String keyBoardCode;
		private String regionIndexCode;

		public String getCameraIndexCode() {
			return cameraIndexCode;
		}

		public void setCameraIndexCode(String cameraIndexCode) {
			this.cameraIndexCode = cameraIndexCode;
		}

		public String getGbIndexCode() {
			return gbIndexCode;
		}

		public void setGbIndexCode(String gbIndexCode) {
			this.gbIndexCode = gbIndexCode;
		}

		public String getCameraName() {
			return cameraName;
		}
		
		public void setCameraName(String cameraName) {
			this.cameraName = cameraName;
		}

		public String getDeviceIndexCode() {
			return deviceIndexCode;
		}

		public void setDeviceIndexCode(String deviceIndexCode) {
			this.deviceIndexCode = deviceIndexCode;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getAltitude() {
			return altitude;
		}

		public void setAltitude(String altitude) {
			this.altitude = altitude;
		}

		public String getPixel() {
			return pixel;
		}

		public void setPixel(String pixel) {
			this.pixel = pixel;
		}

		public String getCameraType() {
			return cameraType;
		}

		public void setCameraType(String cameraType) {
			this.cameraType = cameraType;
		}

		public String getCameraTypeName() {
			return cameraTypeName;
		}

		public void setCameraTypeName(String cameraTypeName) {
			this.cameraTypeName = cameraTypeName;
		}

		public String getInstallPlace() {
			return installPlace;
		}

		public void setInstallPlace(String installPlace) {
			this.installPlace = installPlace;
		}

		public String getMatrixCode() {
			return matrixCode;
		}

		public void setMatrixCode(String matrixCode) {
			this.matrixCode = matrixCode;
		}

		public String getChannelNo() {
			return channelNo;
		}
		
		public void setChannelNo(String channelNo) {
			this.channelNo = channelNo;
		}

		public String getViewshed() {
			return viewshed;
		}

		public void setViewshed(String viewshed) {
			this.viewshed = viewshed;
		}

		public String getCapabilitySet() {
			return capabilitySet;
		}

		public void setCapabilitySet(String capabilitySet) {
			this.capabilitySet = capabilitySet;
		}

		public String getCapabilitySetName() {
			return capabilitySetName;
		}

		public void setCapabilitySetName(String capabilitySetName) {
			this.capabilitySetName = capabilitySetName;
		}

		public String getIntelligentSet() {
			return intelligentSet;
		}

		public void setIntelligentSet(String intelligentSet) {
			this.intelligentSet = intelligentSet;
		}

		public String getIntelligentSetName() {
			return intelligentSetName;
		}

		public void setIntelligentSetName(String intelligentSetName) {
			this.intelligentSetName = intelligentSetName;
		}

		public String getRecordLocation() {
			return recordLocation;
		}

		public void setRecordLocation(String recordLocation) {
			this.recordLocation = recordLocation;
		}

		public String getRecordLocationName() {
			return recordLocationName;
		}

		public void setRecordLocationName(String recordLocationName) {
			this.recordLocationName = recordLocationName;
		}

		public String getPtz() {
			return ptz;
		}

		public void setPtz(String ptz) {
			this.ptz = ptz;
		}

		public String getPtzName() {
			return ptzName;
		}

		public void setPtzName(String ptzName) {
			this.ptzName = ptzName;
		}

		public String getPtzController() {
			return ptzController;
		}

		public void setPtzController(String ptzController) {
			this.ptzController = ptzController;
		}

		public String getPtzControllerName() {
			return ptzControllerName;
		}

		public void setPtzControllerName(String ptzControllerName) {
			this.ptzControllerName = ptzControllerName;
		}

		public String getDeviceResourceType() {
			return deviceResourceType;
		}

		public void setDeviceResourceType(String deviceResourceType) {
			this.deviceResourceType = deviceResourceType;
		}

		public String getDeviceResourceTypeName() {
			return deviceResourceTypeName;
		}

		public void setDeviceResourceTypeName(String deviceResourceTypeName) {
			this.deviceResourceTypeName = deviceResourceTypeName;
		}

		public String getChannelType() {
			return channelType;
		}

		public void setChannelType(String channelType) {
			this.channelType = channelType;
		}

		public String getChannelTypeName() {
			return channelTypeName;
		}

		public void setChannelTypeName(String channelTypeName) {
			this.channelTypeName = channelTypeName;
		}

		public String getTransType() {
			return transType;
		}

		public void setTransType(String transType) {
			this.transType = transType;
		}

		public String getTransTypeName() {
			return transTypeName;
		}

		public void setTransTypeName(String transTypeName) {
			this.transTypeName = transTypeName;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public String getUnitIndexCode() {
			return unitIndexCode;
		}

		public void setUnitIndexCode(String unitIndexCode) {
			this.unitIndexCode = unitIndexCode;
		}

		public String getTreatyType() {
			return treatyType;
		}

		public void setTreatyType(String treatyType) {
			this.treatyType = treatyType;
		}

		public String getTreatyTypeName() {
			return treatyTypeName;
		}

		public void setTreatyTypeName(String treatyTypeName) {
			this.treatyTypeName = treatyTypeName;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}
		
		public String getEncodeDevIndexCode() {
			return encodeDevIndexCode;
		}
		
		public void setEncodeDevIndexCode(String encodeDevIndexCode) {
			this.encodeDevIndexCode = encodeDevIndexCode;
		}
		
		public String getEncodeDevResourceType() {
			return encodeDevResourceType;
		}
		
		public void setEncodeDevResourceType(String encodeDevResourceType) {
			this.encodeDevResourceType = encodeDevResourceType;
		}
		
		public String getEncodeDevResourceTypeName() {
			return encodeDevResourceTypeName;
		}
		
		public void setEncodeDevResourceTypeName(String encodeDevResourceTypeName) {
			this.encodeDevResourceTypeName = encodeDevResourceTypeName;
		}
		
		public String getInstallLocation() {
			return installLocation;
		}
		
		public void setInstallLocation(String installLocation) {
			this.installLocation = installLocation;
		}
		
		public String getKeyBoardCode() {
			return keyBoardCode;
		}
		
		public void setKeyBoardCode(String keyBoardCode) {
			this.keyBoardCode = keyBoardCode;
		}
		
		public String getRegionIndexCode() {
			return regionIndexCode;
		}
		
		public void setRegionIndexCode(String regionIndexCode) {
			this.regionIndexCode = regionIndexCode;
		}

	}

}

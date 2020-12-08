package com.gytech.vo;

import java.io.Serializable;

/**
 * 获取区域列表请求实体
 * @author Lenovo
 *
 */
public class GetAreaRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2995016997965140064L;
	private Integer pageNo;
	private Integer pageSize;
	private String treeCode;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTreeCode() {
		return treeCode;
	}
	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

}

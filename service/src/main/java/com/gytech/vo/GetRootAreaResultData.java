package com.gytech.vo;

import java.io.Serializable;

/**
 * 获取区域列表返回实体
 * 
 * @author limap
 *
 */
public class GetRootAreaResultData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 785156422209249946L;
	private String indexCode;
	private String name;
	private String parentIndexCode;
	private String treeCode;

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentIndexCode() {
		return parentIndexCode;
	}

	public void setParentIndexCode(String parentIndexCode) {
		this.parentIndexCode = parentIndexCode;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

}

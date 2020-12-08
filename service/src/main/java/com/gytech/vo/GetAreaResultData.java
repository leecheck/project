package com.gytech.vo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 	分页获取区域列表返回实体
 * @author limap
 *
 */
public class GetAreaResultData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8906228207875417800L;
	private Integer total;
	private Integer pageNo;
	private Integer pageSize;
	private List<Node> list = Lists.newArrayList();
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
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
	public List<Node> getList() {
		return list;
	}
	public void setList(List<Node> list) {
		this.list = list;
	}


	public static class Node implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7168751652573216787L;
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

}

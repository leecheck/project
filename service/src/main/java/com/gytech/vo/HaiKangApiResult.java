package com.gytech.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 海康api接口返回结果通用实体
 * @author limap
 *
 * @param <T>
 */
public class HaiKangApiResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5339979489775449386L;
	private final String success_code = "0";
	
	@JSONField(serialize = false)
	private String code = "";
	private String msg = "";
	private T data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success_code.equals(code);
	}

}

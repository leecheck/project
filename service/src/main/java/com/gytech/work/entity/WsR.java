package com.gytech.work.entity;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2016年10月27日 下午9:59:27
 */
public class WsR {
	private static final long serialVersionUID = 1L;

	private String type;
	private Integer code;
	private String msg;
	private Object data;

	public WsR() {
		this.type = "error";
		this.code = 0;
		this.msg = "";
		this.data = null;
	}
	
	public static WsR error() {
		return new WsR();
	}

	public static WsR ok() {
		WsR ok = new WsR();
		ok.setCode(1);
		return ok;
	}

	public WsR success() {
		this.setCode(1);
		return this;
	}

	public WsR msg(String msg){
		this.setMsg(msg);
		return this;
	}

	public WsR type(String type){
		this.setType(type);
		return this;
	}

	public WsR data(Object data){
		this.setData(data);
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

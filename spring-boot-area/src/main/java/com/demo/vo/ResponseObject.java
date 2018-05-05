package com.demo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Map;

public class ResponseObject<T> implements Serializable {

	private static final long serialVersionUID = 5014379068811962022L;
	private int code;
	private String msg;
	@JsonIgnore
	private String body;
	private T data;
	@JsonIgnore
	private Map<String, Object> params;

	/**
	 * 如果code=0，则表示成功
	 * @return
	 */
	public boolean isSuccess(){
		return code == 0;
	}

	public ResponseObject() {
		this.code = 0;
	}

	public ResponseObject(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@JsonIgnore
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public ResponseObject setResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}
	public ResponseObject setResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		return this;
	}
}

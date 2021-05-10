package com.mgs.bean;

public class QueryMerchantConfigBean {
             

	public String getConfig_key() {
		return config_key;
	}
	public void setConfig_key(String config_key) {
		this.config_key = config_key;
	}

	public String getEncodeString() {
		return encodeString;
	}
	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}
	
	
	private String config_key = "";
	private String encodeString = "";
	
	
	
	private String extend_param = "";


	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}
	
	
}

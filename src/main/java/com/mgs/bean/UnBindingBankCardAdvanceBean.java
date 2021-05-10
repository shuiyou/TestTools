package com.mgs.bean;

public class UnBindingBankCardAdvanceBean {

	private String identity_id = "";
	private String identity_type = "";
	private String ticket = "";
	private String valid_code = "";
	private String client_ip = "";	
	private String extend_param = "";
	private String encodeString = "";

	public String getIdentity_id() {
		return identity_id;
	}

	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}
	
	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

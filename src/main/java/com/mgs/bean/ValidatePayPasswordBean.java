package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class ValidatePayPasswordBean {

	private String identity_id = "";
	private String identity_type = "";
	private String request_no = "";
	public String getCashdesk_addr_category() {
		return cashdesk_addr_category;
	}

	public void setCashdesk_addr_category(String cashdesk_addr_category) {
		this.cashdesk_addr_category = cashdesk_addr_category;
	}

	private String extend_param = "";
	private String encodeString = "";
	private String cashdesk_addr_category = "";

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
	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
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

package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class BindingVerifyBean {

	private String identity_id = "";
	private String identity_type = "";
	private String verify_type = "";
	private String verify_entity = "";
	private String extend_param = "";
	private String encodeString = "";
	private String client_ip = "";


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

	public String getVerify_type() {
		return verify_type;
	}

	public void setVerify_type(String verify_type) {
		this.verify_type = verify_type;
	}

	public String getVerify_entity() {
		return verify_entity;
	}

	public void setVerify_entity(String verify_entity) {
		this.verify_entity = verify_entity;
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

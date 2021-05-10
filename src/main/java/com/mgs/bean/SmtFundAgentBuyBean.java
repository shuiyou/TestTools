package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class SmtFundAgentBuyBean {

	private String identity_id = "";
	private String identity_type = "";
	private String agent_name = "";
	private String license_no = "";
	private String license_type_code = "";
	private String agent_mobile = "";
	private String email = "";
	private String client_ip = "";
	private String encodeString = "";
	
	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}

	public String getLicense_type_code() {
		return license_type_code;
	}

	public void setLicense_type_code(String license_type_code) {
		this.license_type_code = license_type_code;
	}

	public String getAgent_mobile() {
		return agent_mobile;
	}

	public void setAgent_mobile(String agent_mobile) {
		this.agent_mobile = agent_mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	
	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

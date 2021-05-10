package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class CreateActivateMemberBean {

	private String identity_id = "";
	private String identity_type = "";

	public String getHost_role() {
		return host_role;
	}

	public void setHost_role(String host_role) {
		this.host_role = host_role;
	}

	private String member_type = "";
	private String client_ip = "";
	private String extend_param = "";
	private String encodeString = "";
	private String host_role = "";

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

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
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

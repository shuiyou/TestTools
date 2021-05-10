package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class SmerchantUrlGetBean {

	private String identity_id = "";
	private String identity_type = "";
	private String member_type = "";
	private String url_type = "";
	private String client_ip = "";
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

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
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

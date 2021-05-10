package com.mgs.bean;

public class QueryBalanceBean {

	private String identity_id = "";
	private String identity_type = "";
	private String account_type = "";
	private String extend_param = "";

	public String getAccount_identity() {
		return account_identity;
	}

	public void setAccount_identity(String account_identity) {
		this.account_identity = account_identity;
	}

	private String encodeString = "";
	private String account_identity = "";

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

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
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

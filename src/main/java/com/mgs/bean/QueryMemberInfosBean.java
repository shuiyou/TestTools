package com.mgs.bean;

public class QueryMemberInfosBean {

	private String identity_id = "";
	private String identity_type = "";
	private String member_type = "";
	private String extend_param = "";
	private String encodeString = "";
	public String getIs_mask() {
		return is_mask;
	}

	public void setIs_mask(String is_mask) {
		this.is_mask = is_mask;
	}

	private String is_mask = "";

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

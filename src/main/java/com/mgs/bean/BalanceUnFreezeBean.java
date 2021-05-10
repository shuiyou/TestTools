package com.mgs.bean;

public class BalanceUnFreezeBean {

	private String out_unfreeze_no = "";
	private String out_freeze_no = "";
	private String identity_id = "";
	private String identity_type = "";
	private String summary = "";
	private String amount = "";
	private String client_ip = "";
	private String extend_param = "";
	private String encodeString = "";

	public String getOut_unfreeze_no() {
		return out_unfreeze_no;
	}

	public void setOut_unfreeze_no(String out_unfreeze_no) {
		this.out_unfreeze_no = out_unfreeze_no;
	}

	public String getOut_freeze_no() {
		return out_freeze_no;
	}

	public void setOut_freeze_no(String out_freeze_no) {
		this.out_freeze_no = out_freeze_no;
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
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

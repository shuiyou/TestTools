package com.b2c.bean;

public class CreateBatchPay2BankBean {

	private String batch_no = "";
	private String detail_list = "";
	private String payto_type = "";
	private String extend_param = "";
	private String encodeString = "";
	private String account_type = "";

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(String detail_list) {
		this.detail_list = detail_list;
	}

	public String getPayto_type() {
		return payto_type;
	}

	public void setPayto_type(String payto_type) {
		this.payto_type = payto_type;
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

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) { 	this.account_type = account_type; }

}

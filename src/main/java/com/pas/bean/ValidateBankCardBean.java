package com.pas.bean;

public class ValidateBankCardBean {

	private String out_request_no = "";
	private String bank_code = "";
	private String bank_account_no = "";
	private String account_name = "";
	private String card_type = "";
	private String card_attribute = "";
	private String cert_type = "";
	private String cert_no = "";
	private String phone_no = "";
	private String validity_period = "";
	private String verification_value = "";
	private String needAdvance ="";
	private String extend_param = "";
	private String encodeString = "";
	private String card_auth_type="";

/*	public String getRequest_no() {
		return out_request_no;
	}

	public void setRequest_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	
	*/


	public String getCard_auth_type() {
	    return card_auth_type;
	}

	public void setCard_auth_type(String card_auth_type) {
	    this.card_auth_type = card_auth_type;
	}

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_account_no() {
		return bank_account_no;
	}

	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_attribute() {
		return card_attribute;
	}

	public void setCard_attribute(String card_attribute) {
		this.card_attribute = card_attribute;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getValidity_period() {
		return validity_period;
	}

	public void setValidity_period(String validity_period) {
		this.validity_period = validity_period;
	}

	public String getVerification_value() {
		return verification_value;
	}

	public void setVerification_value(String verification_value) {
		this.verification_value = verification_value;
	}
	public String getNeedAdvance() {
		return needAdvance;
	}

	public void setNeedAdvance(String needAdvance) {
		this.needAdvance = needAdvance;
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

package com.ugs.bean;

public class CreateInsureTradeBean {

	private String out_trade_no = "";
	private String payer_identity_id = "";
	private String payer_identity_type = "";
	private String payer_ip = "";
	private String amount = "";	
	private String pay_method = "";
	private String payee_identity_id = "";
	private String payee_identity_type = "";
	private String payee_account_type = "";
	private String summary = "";
	private String trade_close_time = "";
	private String extend_param = "";
	private String encodeString = "";

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getPayer_identity_id() {
		return payer_identity_id;
	}

	public void setPayer_identity_id(String payer_identity_id) {
		this.payer_identity_id = payer_identity_id;
	}

	public String getPayer_identity_type() {
		return payer_identity_type;
	}

	public void setPayer_identity_type(String payer_identity_type) {
		this.payer_identity_type = payer_identity_type;
	}
	public String getPayer_ip() {
		return payer_ip;
	}

	public void setPayer_ip(String payer_ip) {
		this.payer_ip = payer_ip;
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

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getPayee_identity_id() {
		return payee_identity_id;
	}

	public void setPayee_identity_id(String payee_identity_id) {
		this.payee_identity_id = payee_identity_id;
	}
	
	public String getPayee_identity_type() {
		return payee_identity_type;
	}

	public void setPayee_identity_type(String payee_identity_type) {
		this.payee_identity_type = payee_identity_type;
	}
	
	public String getPayee_account_type() {
		return payee_account_type;
	}

	public void setPayee_account_type(String payee_account_type) {
		this.payee_account_type = payee_account_type;
	}
	
	public String getTrade_close_time() {
		return trade_close_time;
	}

	public void setTrade_close_time(String trade_close_time) {
		this.trade_close_time = trade_close_time;
	}
	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}


}

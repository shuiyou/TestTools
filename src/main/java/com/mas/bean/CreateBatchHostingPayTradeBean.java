package com.mas.bean;

public class CreateBatchHostingPayTradeBean {

	private String out_pay_no = "";
	private String out_trade_code = "";
	private String trade_list = "";
	private String payee_partner_id = "";
	private String notify_method = "";
	private String extend_param = "";
	private String encodeString = "";
	private String user_ip = "";
	private String payer_partner_id = "";
	
	
	public String getPayer_partner_id() {
		return payer_partner_id;
	}

	public void setPayer_partner_id(String payer_partner_id) {
		this.payer_partner_id = payer_partner_id;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	
	public String getOut_pay_no() {
		return out_pay_no;
	}

	public void setOut_pay_no(String out_pay_no) {
		this.out_pay_no = out_pay_no;
	}

	public String getOut_trade_code() {
		return out_trade_code;
	}

	public void setOut_trade_code(String out_trade_code) {
		this.out_trade_code = out_trade_code;
	}

	public String getTrade_list() {
		return trade_list;
	}

	public void setTrade_list(String trade_list) {
		this.trade_list = trade_list;
	}

	public String getPayee_partner_id() {
		return payee_partner_id;
	}

	public void setPayee_partner_id(String payee_partner_id) {
		this.payee_partner_id = payee_partner_id;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getNotify_method() {
		return notify_method;
	}

	public void setNotify_method(String notify_method) {
		this.notify_method = notify_method;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

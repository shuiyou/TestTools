package com.mas.bean;

public class CreatePreAuthTradeBean {

	private String out_trade_no = "";
	private String out_trade_code = "";
	private String summary = "";
	private String trade_close_time = "";
	private String extend_param = "";
	private String can_repay_on_failed = "";
	private String payer_id = "";
	private String payer_identity_type = "";
	private String payer_ip = "";
	private String pay_method = "";
	private String user_fee = "";
	private String is_safe_card_support = "";
	private String encodeString = "";
	private String collect_trade_type;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_trade_code() {
		return out_trade_code;
	}

	public void setOut_trade_code(String out_trade_code) {
		this.out_trade_code = out_trade_code;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTrade_close_time() {
		return trade_close_time;
	}

	public void setTrade_close_time(String trade_close_time) {
		this.trade_close_time = trade_close_time;
	}

	public String getCan_repay_on_failed() {
		return can_repay_on_failed;
	}

	public void setCan_repay_on_failed(String can_repay_on_failed) {
		this.can_repay_on_failed = can_repay_on_failed;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
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
	
	public String getCollect_trade_type() {
		return collect_trade_type;
	}
	
	public void setCollect_trade_type(String collect_trade_type) {
		this.collect_trade_type = collect_trade_type;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getUser_fee() {
		return user_fee;
	}

	public void setUser_fee(String user_fee) {
		this.user_fee = user_fee;
	}

	public String getIs_safe_card_support() {
		return is_safe_card_support;
	}

	public void setIs_safe_card_support(String is_safe_card_support) {
		this.is_safe_card_support = is_safe_card_support;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

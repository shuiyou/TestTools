package com.mas.bean;

public class CreateSingleHostingPayToCardTradeBean {

	private String out_trade_no = "";
	private String out_trade_code = "";
	private String collect_method = "";
	private String amount = "";
	private String summary = "";
	private String extend_param = "";
	private String goods_id = "";
	private String encodeString = "";
	private String payto_type= "";
	private String user_ip = "";
	private String trade_related_no = "";

	
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_related_no() {
		return trade_related_no;
	}
	
	public void setTrade_related_no(String trade_related_no) {
		this.trade_related_no = trade_related_no;
	}
	public String getOut_trade_code() {
		return out_trade_code;
	}

	public void setOut_trade_code(String out_trade_code) {
		this.out_trade_code = out_trade_code;
	}

	public String getCollect_method() {
		return collect_method;
	}

	public void setCollect_method(String collect_method) {
		this.collect_method = collect_method;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getPayto_type() {
		return payto_type;
	}

	public void setPayto_type(String payto_type) {
		this.payto_type = payto_type;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

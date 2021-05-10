package com.mas.bean;

public class CreateHostingRefundBean {

	private String out_trade_no = "";
	private String orig_outer_trade_no = "";
	private String refund_amount = "";
	private String user_ip = "";
	private String summary = "";
	private String refund_split_list = "";
	private String refund_split_strategy="";
	private String extend_param = "";
	private String encodeString = "";

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOrig_outer_trade_no() {
		return orig_outer_trade_no;
	}

	public void setOrig_outer_trade_no(String orig_outer_trade_no) {
		this.orig_outer_trade_no = orig_outer_trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String getRefund_split_list() {
		return refund_split_list;
	}

	public void setRefund_split_list(String refund_split_list) {
		this.refund_split_list = refund_split_list;
	}

	public String getRefund_split_strategy() {
		return refund_split_strategy;
	}

	public void setRefund_split_strategy(String refund_split_strategy) {
		this.refund_split_strategy = refund_split_strategy;
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

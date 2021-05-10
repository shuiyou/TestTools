package com.mas.bean;

public class FundCreatePurchaseRefundBean {
	private String out_trade_no = "";
	private String orig_outer_trade_no = "";
	private String summary = "";
	private String extend_param = "";
	private String payer_ip = "";
	private String encodeString = "";
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
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
	public String getPayer_ip() {
		return payer_ip;
	}
	public void setPayer_ip(String payer_ip) {
		this.payer_ip = payer_ip;
	}
	public String getEncodeString() {
		return encodeString;
	}
	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}
	public String getOrig_outer_trade_no() {
		return orig_outer_trade_no;
	}
	public void setOrig_outer_trade_no(String orig_outer_trade_no) {
		this.orig_outer_trade_no = orig_outer_trade_no;
	}
}

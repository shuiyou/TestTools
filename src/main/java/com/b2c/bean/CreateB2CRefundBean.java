package com.b2c.bean;

public class CreateB2CRefundBean {

	private String out_trade_no = "";
	private String refund_amount = "";
	private String orig_out_trade_no = "";
	private String summary = "";
	private String split_list = "";
	private String extend_param = "";
	private String encodeString = "";

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getOrig_out_trade_no() {
		return orig_out_trade_no;
	}

	public void setOrig_out_trade_no(String orig_out_trade_no) {
		this.orig_out_trade_no = orig_out_trade_no;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSplit_list() {
		return split_list;
	}

	public void setSplit_list(String split_list) {
		this.split_list = split_list;
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

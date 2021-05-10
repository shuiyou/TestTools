package com.b2c.bean;

public class ReceiptSplitBean {

	private String encodeString = "";
	private String orig_out_trade_no = "";
	private String split_list = "";
	private String split_type = "";
	public String getOrig_out_trade_no() {
		return orig_out_trade_no;
	}
	public void setOrig_out_trade_no(String orig_out_trade_no) {
		this.orig_out_trade_no = orig_out_trade_no;
	}
	public String getSplit_list() {
		return split_list;
	}
	public void setSplit_list(String split_list) {
		this.split_list = split_list;
	}
	public String getEncodeString() {
		return encodeString;
	}
	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}
	public String getSplit_type() {
		return split_type;
	}
	public void setSplit_type(String split_type) {
		this.split_type = split_type;
	}
	
}

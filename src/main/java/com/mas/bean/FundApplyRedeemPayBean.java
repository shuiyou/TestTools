package com.mas.bean;

public class FundApplyRedeemPayBean {
	private String out_batch_no = "";
	private String trade_date = "";
	private String fund_plain_file = "";
	private String fund_encrypt_file = "";
	private String detail_file = "";
	private String file_digest = "";
	private String extend_param = "";
	private String encodeString = "";
	private String summary = "";
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getOut_batch_no() {
		return out_batch_no;
	}
	public void setOut_batch_no(String out_batch_no) {
		this.out_batch_no = out_batch_no;
	}
	public String getTrade_date() {
		return trade_date;
	}
	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}
	public String getFund_plain_file() {
		return fund_plain_file;
	}
	public void setFund_plain_file(String fund_plain_file) {
		this.fund_plain_file = fund_plain_file;
	}
	public String getFund_encrypt_file() {
		return fund_encrypt_file;
	}
	public void setFund_encrypt_file(String fund_encrypt_file) {
		this.fund_encrypt_file = fund_encrypt_file;
	}
	public String getDetail_file() {
		return detail_file;
	}
	public void setDetail_file(String detail_file) {
		this.detail_file = detail_file;
	}
	public String getFile_digest() {
		return file_digest;
	}
	public void setFile_digest(String file_digest) {
		this.file_digest = file_digest;
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

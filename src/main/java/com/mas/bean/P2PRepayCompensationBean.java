package com.mas.bean;

public class P2PRepayCompensationBean {

	private String out_trade_no = "";
	private String orig_out_trade_no = "";
	private String payer_identity_id = "";
	private String payer_identity_type = "";
	private String payer_fee = "";
	private String can_repay_on_failed = "";

	public String getTrade_close_time() {
		return trade_close_time;
	}

	public void setTrade_close_time(String trade_close_time) {
		this.trade_close_time = trade_close_time;
	}

	private String trade_close_time = "";

    public String getPayer_account_identity() {
        return payer_account_identity;
    }

    public void setPayer_account_identity(String payer_account_identity) {
        this.payer_account_identity = payer_account_identity;
    }

    private String extend_param = "";
	private String payer_ip = "";
	private String pay_method = "";
	private String encodeString = "";
	private String payer_account_identity = "";

	private String summary = "";

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOrig_out_trade_no() {
		return orig_out_trade_no;
	}

	public void setOrig_out_trade_no(String orig_out_trade_no) {
		this.orig_out_trade_no = orig_out_trade_no;
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

	public String getPayer_fee() {
		return payer_fee;
	}

	public void setPayer_fee(String payer_fee) {
		this.payer_fee = payer_fee;
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

	public String getPayer_ip() {
		return payer_ip;
	}

	public void setPayer_ip(String payer_ip) {
		this.payer_ip = payer_ip;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

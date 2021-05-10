package com.sh_bank.bean;

public class AccountFundTransfer_shBean {
    private String out_trade_no = "";
    private String out_trade_code = "";
    private String payer_identity_id = "";
    private String payer_identity_type = "";
    private String payer_account_type = "";
    private String payee_identity_id = "";
    private String payee_identity_type = "";
    private String payee_account_type = "";
    private String amount = "";
    private String summary = "";
    private String gift_money = "";
    private String out_bid_no = "";
    private String extend_param = "";
    private String encodeString = "";
    
    public String getGift_money() {
		return gift_money;
	}
	public void setGift_money(String gift_money) {
		this.gift_money = gift_money;
	}
	public String getOut_bid_no() {
		return out_bid_no;
	}
	public void setOut_bid_no(String out_bid_no) {
		this.out_bid_no = out_bid_no;
	}
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
    public String getPayer_account_type() {
        return payer_account_type;
    }
    public void setPayer_account_type(String payer_account_type) {
        this.payer_account_type = payer_account_type;
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

package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/8/15.
 */
public class AccountFundoutPage_shBean {

    private String out_trade_no;
    private String out_trade_code;
    private String identity_type;

    public String getAccount_identity() {
        return account_identity;
    }

    public void setAccount_identity(String account_identity) {
        this.account_identity = account_identity;
    }

    private String identity_id;

    private String amount;
    private String card_id;
    private String user_fee;
    private String summary;
    private String account_identity;

    private String extend_param;
    private String encodeString = "";

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

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getUser_fee() {
        return user_fee;
    }

    public void setUser_fee(String user_fee) {
        this.user_fee = user_fee;
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

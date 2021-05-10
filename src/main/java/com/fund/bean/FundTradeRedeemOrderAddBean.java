package com.fund.bean;

/**
 * Created by liulanhong
 *  */
public class FundTradeRedeemOrderAddBean {
 private String member_id = "";
    private String share_id = "";
    private String trade_account_no = "";
    private String fund_code = "";
    private String payment_type = "";
    private String out_request_no = "";
 private String trade_share = "";
 private String large_flag = "";
 private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getShare_id() {
        return share_id;
    }

    public void setShare_id(String share_id) {
        this.share_id = share_id;
    }

    public String getTrade_account_no() {
        return trade_account_no;
    }

    public void setTrade_account_no(String trade_account_no) {
        this.trade_account_no = trade_account_no;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getTrade_share() {
        return trade_share;
    }

    public void setTrade_share(String trade_share) {
        this.trade_share = trade_share;
    }

    public String getLarge_flag() {
        return large_flag;
    }

    public void setLarge_flag(String large_flag) {
        this.large_flag = large_flag;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

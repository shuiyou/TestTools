package com.fund.bean;


public class FundDividendMethodOrderAddBean {
    private String member_id = "";
    private String share_id = "";
    private String trade_account_no = "";
    private String fund_code = "";
    private String dividend_dethod = "";
    private String out_request_no = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getDividend_dethod() {
        return dividend_dethod;
    }

    public void setDividend_dethod(String dividend_dethod) {
        this.dividend_dethod = dividend_dethod;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

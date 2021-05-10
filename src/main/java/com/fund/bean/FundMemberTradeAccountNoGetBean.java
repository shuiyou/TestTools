package com.fund.bean;

/**
 * Created by dalin on 2017-09-11.
 */
public class FundMemberTradeAccountNoGetBean {
    private String member_id = "";
    private String payment_method_id = "";
    private String fund_code = "";
    private String portfolio_code="";
    private String encodeString = "";

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getPortfolio_code() {
        return portfolio_code;
    }

    public void setPortfolio_code(String portfolio_code) {
        this.portfolio_code = portfolio_code;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }
}

package com.fund.bean;

/**
 * Created by YangGaoMin on 2017-12-06.
 */
public class FundRateAndDiscountGetBean {
    private String fund_code = "";
    private String busin_code = "";
    private String cust_type = "";
    private String encodeString = "";
    private String other_fund_code="";

    public String getOther_fund_code() {
        return other_fund_code;
    }

    public void setOther_fund_code(String other_fund_code) {
        this.other_fund_code = other_fund_code;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getBusin_code() {
        return busin_code;
    }

    public void setBusin_code(String busin_code) {
        this.busin_code = busin_code;
    }

    public String getCust_type() {
        return cust_type;
    }

    public void setCust_type(String cust_type) {
        this.cust_type = cust_type;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

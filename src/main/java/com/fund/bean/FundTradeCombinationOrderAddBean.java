package com.fund.bean;

/**
 * Created by yimeiweile on 2017/6/30.
 */
public class FundTradeCombinationOrderAddBean {
    private String member_id = "";
    private String payment_method_id = "";
    private String payment_type = "";
    private String out_request_no = "";
    private String portfolio_apply_no="";
    private String overstep_risk_level = "";
    private String risk_notice = "";
    private String client_ip = "";
    private String mac_address = "";
    private String portfolio_code = "";
    private String combination_details = "";
    private String encodeString = "";

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

    public String getPortfolio_apply_no() {
        return portfolio_apply_no;
    }

    public void setPortfolio_apply_no(String portfolio_apply_no) {
        this.portfolio_apply_no = portfolio_apply_no;
    }

    public String getOverstep_risk_level() {
        return overstep_risk_level;
    }

    public void setOverstep_risk_level(String overstep_risk_level) {
        this.overstep_risk_level = overstep_risk_level;
    }

    public String getRisk_notice() {
        return risk_notice;
    }

    public void setRisk_notice(String risk_notice) {
        this.risk_notice = risk_notice;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getPortfolio_code() {
        return portfolio_code;
    }

    public void setPortfolio_code(String portfolio_code) {
        this.portfolio_code = portfolio_code;
    }

    public String getCombination_details() {
        return combination_details;
    }

    public void setCombination_details(String combination_details) {
        this.combination_details = combination_details;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

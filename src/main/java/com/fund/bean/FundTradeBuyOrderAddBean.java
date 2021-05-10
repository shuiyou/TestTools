package com.fund.bean;

/**
 * Created by yimeiweile on 2017/6/30.
 */
public class FundTradeBuyOrderAddBean {
    private String member_id = "";
    private String payment_method_id = "";
    private String payment_type = "";
    private String out_request_no = "";
    private String busin_code = "";
    private String fund_code = "";
    private String trade_amount = "";
    private String overstep_risk_level = "";
    private String risk_notice = "";
    private String client_ip = "";
    private String mac_address = "";
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

    public String getBusin_code() {
        return busin_code;
    }

    public void setBusin_code(String busin_code) {
        this.busin_code = busin_code;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
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

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

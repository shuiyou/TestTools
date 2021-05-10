package com.fund.bean;

/**
 * Created by liulanhong on 2017/7/1.
 */
public class FundTradeRedeemAndBuyOrderAddBean {

    private String member_id = "";
    private String share_id = "";
    private String trade_account_no = "";
    private String fund_code = "";
    private String out_request_no = "";
    private String dest_fund_code = "";
    private String trade_share = "";
    private String large_flag = "";
    private String overstep_risk_level = "";
    private String risk_notice = "";
    private String client_ip = "";
    private String mac_address = "";

    public String getOverstep_risk_level() {
        return overstep_risk_level;
    }

    public void setOverstep_risk_level(String overstep_risk_level) {
        this.overstep_risk_level = overstep_risk_level;
    }

    private String payment_type = "";
    private String encodeString = "";


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

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getDest_fund_code() {
        return dest_fund_code;
    }

    public void setDest_fund_code(String dest_fund_code) {
        this.dest_fund_code = dest_fund_code;
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

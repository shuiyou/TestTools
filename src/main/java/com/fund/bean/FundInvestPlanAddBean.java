package com.fund.bean;

public class FundInvestPlanAddBean {

    private String member_id = "";
    private String payment_method_id = "";
    private String payment_type = "";
    private String out_request_no = "";
    private String fund_code = "";
    private String trade_amount = "";
    private String cycle_unit = "";
    private String send_day = "";
    private String trade_cycle = "";
    private String retry_times = "";
    private String terminate_times = "";
    private String begin_date = "";
    private String end_date = "";
    private String overstep_risk_level="";
    private String risk_notice = "";
    private String client_ip = "";

    public String getOverstep_risk_level() {
        return overstep_risk_level;
    }

    public void setOverstep_risk_level(String overstep_risk_level) {
        this.overstep_risk_level = overstep_risk_level;
    }

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

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getCycle_unit() {
        return cycle_unit;
    }

    public void setCycle_unit(String cycle_unit) {
        this.cycle_unit = cycle_unit;
    }

    public String getSend_day() {
        return send_day;
    }

    public void setSend_day(String send_day) {
        this.send_day = send_day;
    }

    public String getTrade_cycle() {
        return trade_cycle;
    }

    public void setTrade_cycle(String trade_cycle) {
        this.trade_cycle = trade_cycle;
    }

    public String getTerminate_times() {
        return terminate_times;
    }

    public void setTerminate_times(String terminate_times) {
        this.terminate_times = terminate_times;
    }

    public String getRetry_times() {
        return retry_times;
    }

    public void setRetry_times(String retry_times) {
        this.retry_times = retry_times;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

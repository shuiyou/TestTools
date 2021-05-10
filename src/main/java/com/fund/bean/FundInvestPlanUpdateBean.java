package com.fund.bean;



public class FundInvestPlanUpdateBean {


    private String trade_amount = "";
    private String member_id="";
    private String cycle_unit = "";
    private String send_day = "";
    private String trade_cycle = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    private String retry_times = "";
    private String terminate_times = "";
    private String begin_date = "";
    private String end_date = "";
    private String protocol_no = "";
    private String encodeString = "";

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
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

    public String getRetry_times() {
        return retry_times;
    }

    public void setRetry_times(String retry_times) {
        this.retry_times = retry_times;
    }

    public String getTrade_cycle() {
        return trade_cycle;
    }

    public void setTrade_cycle(String trade_cycle) {
        this.trade_cycle = trade_cycle;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getTerminate_times() {
        return terminate_times;
    }

    public void setTerminate_times(String terminate_times) {
        this.terminate_times = terminate_times;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getProtocol_no() {
        return protocol_no;
    }

    public void setProtocol_no(String protocol_no) {
        this.protocol_no = protocol_no;
    }




    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}


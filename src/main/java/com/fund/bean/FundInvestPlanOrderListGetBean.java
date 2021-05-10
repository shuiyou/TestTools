package com.fund.bean;

public class FundInvestPlanOrderListGetBean {

    private String member_id = "";
    private String protocol_no = "";
    private String pay_status_list = "";

    public String getPay_status_list() {
        return pay_status_list;
    }

    public void setPay_status_list(String pay_status_list) {
        this.pay_status_list = pay_status_list;
    }

    private String confirm_status_list = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getProtocol_no() {
        return protocol_no;
    }

    public void setProtocol_no(String protocol_no) {
        this.protocol_no = protocol_no;
    }

    public String getConfirm_status_list() {
        return confirm_status_list;
    }

    public void setConfirm_status_list(String confirm_status_list) {
        this.confirm_status_list = confirm_status_list;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

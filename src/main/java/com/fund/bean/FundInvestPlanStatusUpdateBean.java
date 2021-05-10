package com.fund.bean;

public class FundInvestPlanStatusUpdateBean {


    private String protocol_no = "";
    private String status = "";
    private String member_id= "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    private String encodeString = "";

    public String getProtocol_no() {
        return protocol_no;
    }

    public void setProtocol_no(String protocol_no) {
        this.protocol_no = protocol_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

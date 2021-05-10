package com.fund.bean;

public class FundTaAcctGetBean {

    private String member_id = "";
    private String ta_code="";
    private String encodeString = "";
    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTa_code() {
        return ta_code;
    }

    public void setTa_code(String ta_code) {
        this.ta_code = ta_code;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

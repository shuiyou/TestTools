package com.fund.bean;

public class FundMemberCardBindValidatePushBean {

    private String member_id = "";
    private String payment_id = "";
    private String valid_code = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public String getValid_code() {
        return valid_code;
    }
    public void setValid_code(String valid_code) {
        this.valid_code = valid_code;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

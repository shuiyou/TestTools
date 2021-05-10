package com.fund.bean;

public class FundMemberChangeBankcardCheckBean {

    private String member_id = "";
    private String old_payment_method_id = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getOld_payment_method_id() {
        return old_payment_method_id;
    }

    public void setOld_payment_method_id(String old_payment_method_id) {
        this.old_payment_method_id = old_payment_method_id;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class MemberAccountBalanceGetBean {
    private String member_id = "";
    private String account_type = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

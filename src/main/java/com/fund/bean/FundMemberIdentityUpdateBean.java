package com.fund.bean;

/**
 * Created by Administrator on 2017/7/1.
 */
public class FundMemberIdentityUpdateBean {
    private String member_id="";
    private String login_name = "";
    private String login_name_type = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_name_type() {
        return login_name_type;
    }

    public void setLogin_name_type(String login_name_type) {
        this.login_name_type = login_name_type;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

package com.sh_bank.bean;

/**
 * Created by Administrator on 2017/7/27.
 */
public class QueryBalance_shBean {
    private String identity_id = "";
    private String identity_type = "";
    private String account_type = "";
    private String extend_param = "";
    private String encodeString = "";


    public String getIdentity_id() {
        return identity_id;
    }
    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }
    public String getIdentity_type() {
        return identity_type;
    }
    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }
    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
    public String getExtend_param() {
        return extend_param;
    }
    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }
    public String getEncodeString() {
        return encodeString;
    }
    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

}

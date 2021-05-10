package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/8/2.
 */
public class SignAutoApply_shBean {
    private String identity_id;
    private String identity_type;
    private String extend_param;

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getDay_quota() {
        return day_quota;
    }

    public void setDay_quota(String day_quota) {
        this.day_quota = day_quota;
    }

    public String getAuth_type_whitelist() {
        return auth_type_whitelist;
    }

    public void setAuth_type_whitelist(String auth_type_whitelist) {
        this.auth_type_whitelist = auth_type_whitelist;
    }

    private String encodeString ;
    private String quota;
    private String day_quota;
    private String auth_type_whitelist;



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

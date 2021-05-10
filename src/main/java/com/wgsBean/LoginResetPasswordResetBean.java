package com.wgsBean;

/**
 * Created by Administrator on 2017/11/17.
 */
public class LoginResetPasswordResetBean {
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getDevice_identity() {
        return device_identity;
    }

    public void setDevice_identity(String device_identity) {
        this.device_identity = device_identity;
    }

    public String getSalt_id() {
        return salt_id;
    }

    public void setSalt_id(String salt_id) {
        this.salt_id = salt_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String mobile="";
    private String reset_token="";
    private String device_identity="";
    private String salt_id="";
    private String password="";
    private String salt="";

}

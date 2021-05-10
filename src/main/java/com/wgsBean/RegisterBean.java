package com.wgsBean;

/**
 * Created by Administrator on 2017/11/15.
 */
public class RegisterBean {
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegister_token() {
        return register_token;
    }

    public void setRegister_token(String register_token) {
        this.register_token = register_token;
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

    private String mobile = "";
    private String register_token = "";
    private String device_identity = "";
    private String salt_id = "";
    private String password = "";

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String salt = "";
}

package com.wgsBean;

/**
 * Created by Administrator on 2017/11/17.
 */
public class LoginResetPasswordCheckRealNameBean {
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerify_token() {
        return verify_token;
    }

    public void setVerify_token(String verify_token) {
        this.verify_token = verify_token;
    }

    public String getSalt_id() {
        return salt_id;
    }

    public void setSalt_id(String salt_id) {
        this.salt_id = salt_id;
    }

    public String getIc_no() {
        return ic_no;
    }

    public void setIc_no(String ic_no) {
        this.ic_no = ic_no;
    }



    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String salt = "";
    private String mobile = "";
    private String verify_token ="";
    private String salt_id = "";
    private String ic_no = "";
}

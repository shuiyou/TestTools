package com.wgsBean;

/**
 * Created by Administrator on 2017/11/30.
 */
public class PersonalBankCardBindingAdBean {
    public String getVerify_token() {
        return verify_token;
    }

    public void setVerify_token(String verify_token) {
        this.verify_token = verify_token;
    }

    public String getValid_code() {
        return valid_code;
    }

    public void setValid_code(String valid_code) {
        this.valid_code = valid_code;
    }

    private String verify_token="";
    private String valid_code="";




}

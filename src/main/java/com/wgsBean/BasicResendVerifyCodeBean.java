package com.wgsBean;

/**
 * Created by Administrator on 2017/12/7.
 */
public class BasicResendVerifyCodeBean {
    public String getVerify_token() {
        return verify_token;
    }

    public void setVerify_token(String verify_token) {
        this.verify_token = verify_token;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSalt_id() {
        return salt_id;
    }

    public void setSalt_id(String salt_id) {
        this.salt_id = salt_id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String salt_id="";
    private String salt="";
    private String verify_token="";
    private String scene="";

}

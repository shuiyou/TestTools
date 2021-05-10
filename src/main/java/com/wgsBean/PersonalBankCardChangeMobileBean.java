package com.wgsBean;

/**
 * Created by Administrator on 2017/12/4.
 */
public class PersonalBankCardChangeMobileBean {
    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_mobile() {
        return card_mobile;
    }

    public void setCard_mobile(String card_mobile) {
        this.card_mobile = card_mobile;
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

    private String salt="";
    private String card_id="";
    private String card_mobile="";
    private String salt_id="";

}

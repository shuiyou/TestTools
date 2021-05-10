package com.wgsBean;

/**
 * Created by Administrator on 2017/11/29.
 */
public class PersonalBankCardQueryCardBinBean {
    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
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
    private String card_no ="";
    private String salt_id ="";

}

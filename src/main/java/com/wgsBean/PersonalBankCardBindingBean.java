package com.wgsBean;

/**
 * Created by Administrator on 2017/11/29.
 */
public class PersonalBankCardBindingBean {

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_mobile() {
        return card_mobile;
    }

    public void setCard_mobile(String card_mobile) {
        this.card_mobile = card_mobile;
    }

    public String getValid_period() {
        return valid_period;
    }

    public void setValid_period(String valid_period) {
        this.valid_period = valid_period;
    }

    public String getSafe_code() {
        return safe_code;
    }

    public void setSafe_code(String safe_code) {
        this.safe_code = safe_code;
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
    private String card_no="";
    private String bank_code="";
    private String card_type="";
    private String card_mobile="";
    private String valid_period="";
    private String safe_code="";
    private String salt_id="";


}

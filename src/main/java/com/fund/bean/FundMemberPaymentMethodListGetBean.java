package com.fund.bean;

/**
 * Created by Liulanhong on 2017/7/1.
 */
public class FundMemberPaymentMethodListGetBean {

    private String member_id = "";
    private String payment_method_id = "";
    private String trade_account_no= "";
    private String main_card_flag= "";
    private String encodeString = "";

    public String getMain_card_flag() {
        return main_card_flag;
    }

    public void setMain_card_flag(String main_card_flag) {
        this.main_card_flag = main_card_flag;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getTrade_account_no() {
        return trade_account_no;
    }

    public void setTrade_account_no(String trade_account_no) {
        this.trade_account_no = trade_account_no;
    }
    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}


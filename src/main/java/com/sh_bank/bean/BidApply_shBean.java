package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/7/28.
 */
public class BidApply_shBean {
    private String out_trade_no;
    private String identity_id ;
    private String identity_type;
    private String amount;

    public String getGift_money() {
        return gift_money;
    }

    public void setGift_money(String gift_money) {
        this.gift_money = gift_money;
    }

    private String out_bid_no;
    private String encodeString;
    private String out_trade_code;
    private String gift_money;

    public String getOut_trade_code() {
        return out_trade_code;
    }

    public void setOut_trade_code(String out_trade_code) {
        this.out_trade_code = out_trade_code;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOut_bid_no() {
        return out_bid_no;
    }

    public void setOut_bid_no(String out_bid_no) {
        this.out_bid_no = out_bid_no;
    }

    public String getExtend_param() {
        return extend_param;
    }

    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }

    private String extend_param;
}

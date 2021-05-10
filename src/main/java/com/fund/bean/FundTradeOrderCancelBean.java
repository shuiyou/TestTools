package com.fund.bean;

/**
 * Created by liulanhong on 2017/7/1.
 */
public class FundTradeOrderCancelBean {
    private String member_id = "";
    private String org_trade_order_no = "";
    private String whether_combination="";
    private String encodeString = "";

    public String getWhether_combination() {
        return whether_combination;
    }

    public void setWhether_combination(String whether_combination) {
        this.whether_combination = whether_combination;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getOrg_trade_order_no() {
        return org_trade_order_no;
    }

    public void setOrg_trade_order_no(String org_trade_order_no) {
        this.org_trade_order_no = org_trade_order_no;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

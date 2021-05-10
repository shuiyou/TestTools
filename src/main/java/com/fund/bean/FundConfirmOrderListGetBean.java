package com.fund.bean;

/**
 * Created by Administrator on 2017/7/13.
 */
public class FundConfirmOrderListGetBean {
    private String member_id = "";
    private String trade_order_no = "";
    private String fund_code_list = "";
    private String filter_fund_code_list= "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTrade_order_no() {
        return trade_order_no;
    }

    public void setTrade_order_no(String trade_order_no) {
        this.trade_order_no = trade_order_no;
    }

    public String getFund_code_list() {
        return fund_code_list;
    }

    public void setFund_code_list(String fund_code_list) {
        this.fund_code_list = fund_code_list;
    }

    public String getFilter_fund_code_list() {
        return filter_fund_code_list;
    }

    public void setFilter_fund_code_list(String filter_fund_code_list) {
        this.filter_fund_code_list = filter_fund_code_list;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

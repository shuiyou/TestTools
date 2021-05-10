package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class FundMemberShareListGetBean {
    private String member_id = "";
    private String fund_code_list = "";
    private String trade_account_no = "";
    private String filter_fund_code_list = "";
    private String encodeString = "";

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getFund_code_list() {
        return fund_code_list;
    }

    public void setFund_code_list(String fund_code_list) {
        this.fund_code_list = fund_code_list;
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

    public String getFilter_fund_code_list() {
        return filter_fund_code_list;
    }

    public void setFilter_fund_code_list(String filter_fund_code_list) {
        this.filter_fund_code_list = filter_fund_code_list;
    }
}

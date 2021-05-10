package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class FundMemberTotalProfitGetBean {
    private String member_id = "";
    private String fund_code_list = "";
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

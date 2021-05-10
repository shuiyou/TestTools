package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class FundOrderListGetBean {
    private String member_id = "";
    private String trade_order_no = "";
    private String fund_code_list = "";
    private String filter_fund_code_list = "";
    private String pay_status_list = "";
    private String confirm_status_list = "";
    private String busin_code_list = "";
    private String encodeString = "";
    private String portfolio_apply_no = "";

    public String getPortfolio_apply_no() {
        return portfolio_apply_no;
    }

    public void setPortfolio_apply_no(String portfolio_apply_no) {
        this.portfolio_apply_no = portfolio_apply_no;
    }

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

    public String getPay_status_list() {
        return pay_status_list;
    }

    public void setPay_status_list(String pay_status_list) {
        this.pay_status_list = pay_status_list;
    }

    public String getFilter_fund_code_list() {
        return filter_fund_code_list;
    }

    public void setFilter_fund_code_list(String filter_fund_code_list) {
        this.filter_fund_code_list = filter_fund_code_list;
    }

    public String getConfirm_status_list() {
        return confirm_status_list;
    }

    public void setConfirm_status_list(String confirm_status_list) {
        this.confirm_status_list = confirm_status_list;
    }

    public String getBusin_code_list() {
        return busin_code_list;
    }

    public void setBusin_code_list(String busin_code_list) {
        this.busin_code_list = busin_code_list;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

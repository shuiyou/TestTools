package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class FundEstimateBuyFeeGetBean {
    private String fund_code = "";
    private String busin_code = "";
    private String trade_amount = "";
    private String encodeString = "";

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getBusin_code() {
        return busin_code;
    }

    public void setBusin_code(String busin_code) {
        this.busin_code = busin_code;
    }

    public String getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(String trade_amount) {
        this.trade_amount = trade_amount;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

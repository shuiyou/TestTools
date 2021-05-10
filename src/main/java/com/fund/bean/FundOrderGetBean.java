package com.fund.bean;

/**
 * Created by yimeiweile on 2017/7/1.
 */
public class FundOrderGetBean {
    private String out_request_no = "";
    private String trade_order_no = "";
    private String encodeString = "";

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getTrade_order_no() {
        return trade_order_no;
    }

    public void setTrade_order_no(String trade_order_no) {
        this.trade_order_no = trade_order_no;
    }



    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

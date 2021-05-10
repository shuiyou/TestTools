package com.mas.bean;

/**
 * Created by klaus on 2018/7/13.
 */
public class ExtendSettleTimeBean {
    private String out_request_no = "";
    private String out_trade_no = "";
    private String extend_time = "";
    private String summary = "";
    private String extend_param = "";
    private String encodeString = "";

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getOut_request_no() {

        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getExtend_time() {
        return extend_time;
    }

    public void setExtend_time(String extend_time) {
        this.extend_time = extend_time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExtend_param() {
        return extend_param;
    }

    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }
}

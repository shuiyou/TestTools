package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/7/27.
 */
public class CancelBidInfo_shBean {
    private String out_bid_no = "";
    private String summary = "";
    private String extend_param = "";
    private String encodeString = "";

    public String getOut_bid_no() {
        return out_bid_no;
    }

    public void setOut_bid_no(String out_bid_no) {
        this.out_bid_no = out_bid_no;
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

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

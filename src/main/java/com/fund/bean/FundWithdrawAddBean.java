package com.fund.bean;

/**
 * Created by sunjie on 2017/6/22.
 */
public class FundWithdrawAddBean {

    private String member_id = "";
    private String amount = "";
    private String arrival_type = "";
    private String out_request_no = "";
    private String encodeString = "";

    public String getArrival_type() {
        return arrival_type;
    }

    public void setArrival_type(String arrival_type) {
        this.arrival_type = arrival_type;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

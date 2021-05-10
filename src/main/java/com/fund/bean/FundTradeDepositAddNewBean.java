package com.fund.bean;

/**
 * Created by yimeiweile on 2018/1/5.
 */
public class FundTradeDepositAddNewBean {
    private String member_id = "";
    private String amount = "";
    private String return_url = "";
    private String user_fee = "";
    private String out_request_no = "";
    private String extension = "";
    private String encodeString = "";

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

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getUser_fee() {
        return user_fee;
    }

    public void setUser_fee(String user_fee) {
        this.user_fee = user_fee;
    }

    public String getOut_request_no() {
        return out_request_no;
    }

    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

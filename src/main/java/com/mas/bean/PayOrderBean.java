package com.mas.bean;

public class PayOrderBean {
    private String out_pay_no = "";
    private String out_trade_no = "";
    private String device_id = "";
    private String reported_identity_type = "";
    private String reported_identity_id = "";
    private String product_desc = "";
    private String payer_ip = "";
    private String pay_method = "";
    private String cashdesk_addr_category = "";
    private String encodeString = "";

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getCashdesk_addr_category() {
        return cashdesk_addr_category;
    }

    public void setCashdesk_addr_category(String cashdesk_addr_category) {
        this.cashdesk_addr_category = cashdesk_addr_category;
    }

    public String getOut_pay_no() {
        return out_pay_no;
    }

    public void setOut_pay_no(String out_pay_no) {
        this.out_pay_no = out_pay_no;
    }


    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getReported_identity_type() {
        return reported_identity_type;
    }

    public void setReported_identity_type(String reported_identity_type) {
        this.reported_identity_type = reported_identity_type;
    }

    public String getReported_identity_id() {
        return reported_identity_id;
    }

    public void setReported_identity_id(String reported_identity_id) {
        this.reported_identity_id = reported_identity_id;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getPayer_ip() {
        return payer_ip;
    }

    public void setPayer_ip(String payer_ip) {
        this.payer_ip = payer_ip;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String pay_method) {
        this.pay_method = pay_method;
    }
}

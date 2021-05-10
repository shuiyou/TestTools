package com.consume_pay.bean;

/**
 * Created by HP on 9/19/2017.
 */
public class CreateSingleConsumeTransferBean {
    private String out_trade_no = "";
    private String out_trade_code = "";
    private String payer_identity_id = "";
    private String payer_identity_type = "";
    private String payer_account_type = "";
    private String payer_ip = "";
    private String payee_identity_id = "";
    private String payee_identity_type = "";
    private String payee_account_type = "";
    private String amount = "";
    private String transfer_mode = "";
    private String payee_fee = "";
    private String payer_fee = "";
    private String extend_param = "";
    private String goods_type = "";
    private String encodeString = "";

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_trade_code() {
        return out_trade_code;
    }

    public void setOut_trade_code(String out_trade_code) {
        this.out_trade_code = out_trade_code;
    }

    public String getPayer_identity_id() {
        return payer_identity_id;
    }

    public void setPayer_identity_id(String payer_identity_id) {
        this.payer_identity_id = payer_identity_id;
    }

    public String getPayer_identity_type() {
        return payer_identity_type;
    }

    public void setPayer_identity_type(String payer_identity_type) {
        this.payer_identity_type = payer_identity_type;
    }

    public String getPayer_account_type() {
        return payer_account_type;
    }

    public void setPayer_account_type(String payer_account_type) {
        this.payer_account_type = payer_account_type;
    }

    public String getPayer_ip() {
        return payer_ip;
    }

    public void setPayer_ip(String payer_ip) {
        this.payer_ip = payer_ip;
    }

    public String getPayee_identity_id() {
        return payee_identity_id;
    }

    public void setPayee_identity_id(String payee_identity_id) {
        this.payee_identity_id = payee_identity_id;
    }

    public String getPayee_identity_type() {
        return payee_identity_type;
    }

    public void setPayee_identity_type(String payee_identity_type) {
        this.payee_identity_type = payee_identity_type;
    }

    public String getPayee_account_type() {
        return payee_account_type;
    }

    public void setPayee_account_type(String payee_account_type) {
        this.payee_account_type = payee_account_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransfer_mode() {
        return transfer_mode;
    }

    public void setTransfer_mode(String transfer_mode) {
        this.transfer_mode = transfer_mode;
    }

    public String getPayee_fee() {
        return payee_fee;
    }

    public void setPayee_fee(String payee_fee) {
        this.payee_fee = payee_fee;
    }

    public String getPayer_fee() {
        return payer_fee;
    }

    public void setPayer_fee(String payer_fee) {
        this.payer_fee = payer_fee;
    }

    public String getExtend_param() {
        return extend_param;
    }

    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }
}

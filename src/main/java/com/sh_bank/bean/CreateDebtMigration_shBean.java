package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/10/25.
 */
public class CreateDebtMigration_shBean {

    private String out_trade_no;
    private String payer_identity_id;
    private String payer_identity_type;
    private String payee_identity_id;
    private String payee_identity_type;
    private String amount;
    private String goods_id;

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    private String summary;
    private String extend_param;
    private String encodeString;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getPayer_identity_id() {
        return payer_identity_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

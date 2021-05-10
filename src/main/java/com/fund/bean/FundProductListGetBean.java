package com.fund.bean;

/**
 * Created by yimeiweile on 2017/6/30.
 */
public class FundProductListGetBean {
    private String manager_code = "";
    private String fund_status = "";
    private String fund_codes = "";



    private String fund_type = "";
    private String risk_level = "";
    private String encodeString = "";

    public String getManager_code() {
        return manager_code;
    }

    public void setManager_code(String manager_code) {
        this.manager_code = manager_code;
    }

    public String getFund_status() {
        return fund_status;
    }

    public void setFund_status(String fund_status) {
        this.fund_status = fund_status;
    }
    public String getFund_codes() {
        return fund_codes;
    }
    public void setFund_codes(String fund_codes) {
        this.fund_codes = fund_codes;
    }


    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
}

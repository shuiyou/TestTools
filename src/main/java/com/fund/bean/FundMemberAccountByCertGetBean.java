package com.fund.bean;

/**
 * Created by dalin on 2017-09-14.
 */
public class FundMemberAccountByCertGetBean {
    private String cert_no = "";
    private String cert_type = "";
    private String encodeString = "";

    public String getEncodeString() {
        return encodeString;
    }

    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }
}

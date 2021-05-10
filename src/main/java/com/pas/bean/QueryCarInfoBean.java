package com.pas.bean;

public class QueryCarInfoBean {
    private String out_request_no = "";
    private String real_name = "";
    private String cert_type = "";
    private String cert_no = "";
    private String licenseNo = "";
    private String carType = "";
    public String getOut_request_no() {
        return out_request_no;
    }
    public void setOut_request_no(String out_request_no) {
        this.out_request_no = out_request_no;
    }
    public String getReal_name() {
        return real_name;
    }
    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }
    public String getCert_type() {
        return cert_type;
    }
    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }
    public String getCert_no() {
        return cert_no;
    }
    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }
    public String getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getEncodeString() {
        return encodeString;
    }
    public void setEncodeString(String encodeString) {
        this.encodeString = encodeString;
    }
    public String getExtend_param() {
        return extend_param;
    }
    public void setExtend_param(String extend_param) {
        this.extend_param = extend_param;
    }
    private String encodeString = "";
    private String extend_param = "";
}

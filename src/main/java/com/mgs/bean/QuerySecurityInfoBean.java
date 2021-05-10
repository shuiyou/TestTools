
/**
 * Created by iynazhai on 16/11/3.
 */



package com.mgs.bean;

public class QuerySecurityInfoBean {

    private String identity_id = "";
    private String identity_type = "";
    private String security_kpi_type = "";
    private String extend_param = "";
    private String encodeString = "";

    public String getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getSecurity_kpi_type() {
        return security_kpi_type;
    }

    public void setSecurity_kpi_type(String security_kpi_type) {
        this.security_kpi_type = security_kpi_type;
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

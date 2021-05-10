package com.sh_bank.bean;

/**
 * Created by Administrator on 2017/7/26.
 */
public class QueryMemberinfos_shBean {
    private String identity_id = "";
    private String identity_type = "";
    private String query_card_flag = "";
    private String query_paypwd_set_flag = "";
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
    public String getQuery_card_flag() {
        return query_card_flag;
    }
    public void setQuery_card_flag(String query_card_flag) {
        this.query_card_flag = query_card_flag;
    }
    public String getQuery_paypwd_set_flag() {
        return query_paypwd_set_flag;
    }
    public void setQuery_paypwd_set_flag(String query_paypwd_set_flag) {
        this.query_paypwd_set_flag = query_paypwd_set_flag;
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

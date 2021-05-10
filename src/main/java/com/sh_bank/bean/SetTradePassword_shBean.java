package com.sh_bank.bean;

/**
 * Created by 尹全旺 on 2017/7/28.
 */
public class SetTradePassword_shBean {
    private String identity_id = "";
    private String identity_type = "";
    private String extend_param = "";
    private String withhold_param = "";
    private String encodeString = "";




    public String getWithhold_param() {
		return withhold_param;
	}

	public void setWithhold_param(String withhold_param) {
		this.withhold_param = withhold_param;
	}

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

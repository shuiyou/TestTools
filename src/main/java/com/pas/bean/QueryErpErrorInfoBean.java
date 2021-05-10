package com.pas.bean;

public class QueryErpErrorInfoBean {
	private String out_request_no = "";
    private String erp_type = "";
    private String erp_param = "";
    private String encodeString = "";
    private String extend_param = "";
    
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
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getErp_type() {
		return erp_type;
	}
	public void setErp_type(String erp_type) {
		this.erp_type = erp_type;
	}
	public String getErp_param() {
		return erp_param;
	}
	public void setErp_param(String erp_param) {
		this.erp_param = erp_param;
	}
    
}

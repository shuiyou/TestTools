package com.mas.bean;

public class AdvanceHostingPayBean {

	private String out_advance_no = "";
	private String ticket = "";
	private String validate_code = "";
	private String extend_param = "";
	private String encodeString = "";
    private String user_ip="";
    
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public String getOut_advance_no() {
		return out_advance_no;
	}

	public void setOut_advance_no(String out_advance_no) {
		this.out_advance_no = out_advance_no;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
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

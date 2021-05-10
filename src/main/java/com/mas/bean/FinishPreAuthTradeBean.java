package com.mas.bean;

public class FinishPreAuthTradeBean {

	private String out_request_no = "";
	private String trade_list = "";
	private String extend_param = "";
	private String encodeString = "";
    private String user_ip="";
    
	
	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
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

	public String getTrade_list() {
		return trade_list;
	}

	public void setTrade_list(String trade_list) {
		this.trade_list = trade_list;
	}

	public String getEncodeString() {
		return encodeString;
	}

	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}

}

package com.wos.bean;

public class TradeWithdrawAddBean extends WosBaseBean {
	private String service;
	private String amount;
	private String member_id;
	private String return_url;
	private String user_fee;
	private String summary;
	
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getUser_fee() {
		return user_fee;
	}
	public void setUser_fee(String user_fee) {
		this.user_fee = user_fee;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}


}

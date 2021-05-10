package com.wos.bean;

public class RegularLoanListBean extends WosBaseBean {
	private String member_id;
	private String goods_id;
	private String debt_status;
	private String service;
	
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getDebt_status() {
		return debt_status;
	}
	public void setDebt_status(String debt_status) {
		this.debt_status = debt_status;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}

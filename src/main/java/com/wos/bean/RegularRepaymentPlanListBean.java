package com.wos.bean;

public class RegularRepaymentPlanListBean extends WosBaseBean {
	private String service;
	private String member_id;
	private String bill_begin_time;
	private String bill_end_time;
	private String bill_status;
	private String goods_id;
	private String repayment_begin_time;
	private String repayment_end_time;
	
	
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
	public String getBill_begin_time() {
		return bill_begin_time;
	}
	public void setBill_begin_time(String bill_begin_time) {
		this.bill_begin_time = bill_begin_time;
	}
	public String getBill_end_time() {
		return bill_end_time;
	}
	public void setBill_end_time(String bill_end_time) {
		this.bill_end_time = bill_end_time;
	}
	public String getBill_status() {
		return bill_status;
	}
	public void setBill_status(String bill_status) {
		this.bill_status = bill_status;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getRepayment_begin_time() {
		return repayment_begin_time;
	}
	public void setRepayment_begin_time(String repayment_begin_time) {
		this.repayment_begin_time = repayment_begin_time;
	}
	public String getRepayment_end_time() {
		return repayment_end_time;
	}
	public void setRepayment_end_time(String repayment_end_time) {
		this.repayment_end_time = repayment_end_time;
	}
}

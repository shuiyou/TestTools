package com.wos.bean;

public class RegularAssetListBean extends WosBaseBean {
	private String member_id;
	private String order_column;
	private String order_direction;
	private String status;
	private String service;
	
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
	public String getOrder_column() {
		return order_column;
	}
	public void setOrder_column(String order_column) {
		this.order_column = order_column;
	}
	public String getOrder_direction() {
		return order_direction;
	}
	public void setOrder_direction(String order_direction) {
		this.order_direction = order_direction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

package com.wos.bean;

public class CouponGetBean extends WosBaseBean {
	private String service;
	private String redpacket_id;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getRedpacket_id() {
		return redpacket_id;
	}
	public void setRedpacket_id(String redpacket_id) {
		this.redpacket_id = redpacket_id;
	}
}

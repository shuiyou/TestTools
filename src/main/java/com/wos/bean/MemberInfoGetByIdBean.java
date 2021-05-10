package com.wos.bean;

public class MemberInfoGetByIdBean extends WosBaseBean {
	private String service;
	private String member_id;
	private String require_verify_infos;
	
	
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
	public String getRequire_verify_infos() {
		return require_verify_infos;
	}
	public void setRequire_verify_infos(String require_verify_infos) {
		this.require_verify_infos = require_verify_infos;
	}
	
}

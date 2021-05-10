package com.wos.bean;

public class PersonalMemberInfoAddBean extends WosBaseBean {
	private String service;
	private String login_name_type;
	private String login_name;
	private String login_pwd;
	private String invite_code;
	
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getLogin_name_type() {
		return login_name_type;
	}
	public void setLogin_name_type(String login_name_type) {
		this.login_name_type = login_name_type;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getLogin_pwd() {
		return login_pwd;
	}
	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	
}

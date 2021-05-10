package com.wos.bean;

public class WosBaseBean {

	private String url = "";
	private String version = "";
	private String request_time = "";
	private String partner_id = "";
	private String sign_type = "";
	private String sign_version = "";
	private String platform_type ="";
	
	public String getPlatform_type() {
		return platform_type;
	}

	public void setPlatform_type(String platform_type) {
		this.platform_type = platform_type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign_version() {
		return sign_version;
	}

	public void setSign_version(String sign_version) {
		this.sign_version = sign_version;
	}
	
	public final String MD5KEY = "1234567890qwertyuiopasdfghjklzxc";
	
	public final String RSAPUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnBuEcKxJByhogleB/rygtyYfGiVrDxDxBKQirdDTOdOy15mhvgXY6AzNfFf5ck0WXfL00SXDwukw3XnKUzGbHl0rXzpLMR+CLpvyLj6QGoBhJ4EUgnfuyFKhbOK2ti5VEgj3ATFIfU+OMiXzv7ybb+LNtFoJ1oXyAk8JnH+KuwIDAQAB";
	
}

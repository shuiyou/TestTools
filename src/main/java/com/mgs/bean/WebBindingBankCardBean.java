package com.mgs.bean;

/**
 * 
 * @author sunjie
 *
 */
public class WebBindingBankCardBean {

	private String identity_id = "";
	private String identity_type = "";
	private String real_name = "";
	private String cert_no = "";
	private String could_bind = "";	
	private String could_unbind = "";
	private String template = "";
	private String extend_param = "";
	private String encodeString = "";

	public String getIdentity_id() {
		return identity_id;
	}

	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}
	public String getCould_bind() {
		return could_bind;
	}

	public void setCould_bind(String could_bind) {
		this.could_bind = could_bind;
	}

	public String getCould_unbind() {
		return could_unbind;
	}

	public void setCould_unbind(String could_unbind) {
		this.could_unbind = could_unbind;
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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

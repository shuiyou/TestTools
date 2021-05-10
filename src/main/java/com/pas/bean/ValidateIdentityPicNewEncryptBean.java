package com.pas.bean;

/**
 * Created by hanxiaodi on 18/4/24.
 */
public class ValidateIdentityPicNewEncryptBean
{

	private String out_request_no = "";
	private String encodeString = "";
	private String pic_file = "";
	private String pic_file2 ="";
	private String extend_param = "";
	public String getEncodeString() {
		return encodeString;
	}
	public void setEncodeString(String encodeString) {
		this.encodeString = encodeString;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getPic_file() {
		return pic_file;
	}
	public void setPic_file(String pic_file) {
		this.pic_file = pic_file;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}
	public String getPic_file2()
	{
		return pic_file2;
	}
	public void setPic_file2(String pic_file2)
	{
		this.pic_file2 = pic_file2;
	}
}

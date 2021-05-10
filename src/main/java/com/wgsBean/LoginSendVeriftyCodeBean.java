package com.wgsBean;

/**
 * Created by hanxiaodi on 17/11/9.
 */
public class LoginSendVeriftyCodeBean
{

	private String mobile = "";
	private String send_type="";
	private String scene = "";



	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getSend_type()
	{
		return send_type;
	}

	public void setSend_type(String send_type)
	{
		this.send_type = send_type;
	}

	public String getScene()
	{
		return scene;
	}

	public void setScene(String scene)
	{
		this.scene = scene;
	}



}

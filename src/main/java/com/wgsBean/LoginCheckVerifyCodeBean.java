package com.wgsBean;

/**
 * Created by hanxiaodi on 17/11/13.
 */
public class LoginCheckVerifyCodeBean
{
	private String mobile;

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getVerify_token()
	{
		return verify_token;
	}

	public void setVerify_token(String verify_token)
	{
		this.verify_token = verify_token;
	}

	public String getDevice_identity()
	{
		return device_identity;
	}

	public void setDevice_identity(String device_identity)
	{
		this.device_identity = device_identity;
	}

	public String getVerify_code()
	{
		return verify_code;
	}

	public void setVerify_code(String verify_code)
	{
		this.verify_code = verify_code;
	}

	private String verify_token;
	private String device_identity;
	private String verify_code;
}

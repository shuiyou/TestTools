package com.wgsBean;

/**
 * Created by hanxiaodi on 17/11/17.
 */
public class LoginResetPasswordCheckVerifyCodeBean
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

	public String getVerify_code()
	{
		return verify_code;
	}

	public void setVerify_code(String verify_code)
	{
		this.verify_code = verify_code;
	}

	private String verify_token;
	private String verify_code;
}

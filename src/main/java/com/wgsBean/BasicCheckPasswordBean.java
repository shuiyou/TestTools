package com.wgsBean;

/**
 * Created by hanxiaodi on 17/12/8.
 */
public class BasicCheckPasswordBean
{
	private String salt_id;

	private String password;

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	private String salt;

	public String getSalt_id()
	{
		return salt_id;
	}

	public void setSalt_id(String salt_id)
	{
		this.salt_id = salt_id;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}



}

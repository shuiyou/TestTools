package com.wgsBean;

/**
 * Created by hanxiaodi on 17/11/24.
 */
public class PersonalCertifyCheckRealNameBean
{
	private String name;

	private String ic_no;

	private String salt_id;

	private String salt;

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIc_no()
	{
		return ic_no;
	}

	public void setIc_no(String ic_no)
	{
		this.ic_no = ic_no;
	}

	public String getSalt_id()
	{
		return salt_id;
	}

	public void setSalt_id(String salt_id)
	{
		this.salt_id = salt_id;
	}



}

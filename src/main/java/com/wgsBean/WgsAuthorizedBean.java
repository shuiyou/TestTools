package com.wgsBean;

/**
 * Created by hanxiaodi on 17/11/20.
 */
public class WgsAuthorizedBean
{
	private String  url = "";
	private String platform = "";
	private String version = "";
	private String service = "";
	private String lang = "";
	private String access_token="";
	private String access_key="";

	public String getAccess_key()
	{
		return access_key;
	}

	public void setAccess_key(String access_key)
	{
		this.access_key = access_key;
	}

	public String getPlatform()
	{
		return platform;
	}

	public void setPlatform(String platform)
	{
		this.platform = platform;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public String getLang()
	{
		return lang;
	}

	public void setLang(String lang)
	{
		this.lang = lang;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getAccess_token()
	{
		return access_token;
	}

	public void setAccess_token(String access_token)
	{
		this.access_token = access_token;
	}
}

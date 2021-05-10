package com.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by hanxiaodi on 18/6/29.
 */
public class HttpResult
{
	private String content;

	private int statusCode;

	public int getStatusCode()
	{
		return statusCode;
	}

	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}


	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString( this, ToStringStyle.SHORT_PREFIX_STYLE );
	}


}

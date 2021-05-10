package com.pas.bean;

/**
 * Created by hanxiaodi on 18/3/27.
 */
public class QueryFlowStatisBean
{
	private String out_request_no = "";
	private String startDate = "";
	private String endDate = "";
	private String statisticServiceName= "";
	private String memo = "";
	private String encodeString = "";

	public String getEncodeString()
	{
		return encodeString;
	}

	public void setEncodeString(String encodeString)
	{
		this.encodeString = encodeString;
	}


	public String getOut_request_no()
	{
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no)
	{
		this.out_request_no = out_request_no;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getStatisticServiceName()
	{
		return statisticServiceName;
	}

	public void setStatisticServiceName(String statisticServiceName)
	{
		this.statisticServiceName = statisticServiceName;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}


}

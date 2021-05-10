package com.ugs.bean;

/**
 * Created by hanxiaodi on 18/5/31.
 */
public class CrossRefundBean {

	private String out_trade_no;
	private String orig_out_trade_no;
	private String summary;
	private String encodeString;

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getOrig_out_trade_no()
	{
		return orig_out_trade_no;
	}

	public void setOrig_out_trade_no(String orig_out_trade_no)
	{
		this.orig_out_trade_no = orig_out_trade_no;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getEncodeString()
	{
		return encodeString;
	}

	public void setEncodeString(String encodeString)
	{
		this.encodeString = encodeString;
	}


}

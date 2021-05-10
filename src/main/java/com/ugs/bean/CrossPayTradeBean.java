package com.ugs.bean;

/**
 * Created by hanxiaodi on 18/5/31.
 */
public class CrossPayTradeBean
{
	private String out_pay_no;
	private String out_trade_no;
	private String payer_ip;
	private String pay_method;
	private String encodeString;

	public String getOut_pay_no()
	{
		return out_pay_no;
	}

	public void setOut_pay_no(String out_pay_no)
	{
		this.out_pay_no = out_pay_no;
	}

	public String getOut_trade_no()
	{
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public String getPayer_ip()
	{
		return payer_ip;
	}

	public void setPayer_ip(String payer_ip)
	{
		this.payer_ip = payer_ip;
	}

	public String getPay_method()
	{
		return pay_method;
	}

	public void setPay_method(String pay_method)
	{
		this.pay_method = pay_method;
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

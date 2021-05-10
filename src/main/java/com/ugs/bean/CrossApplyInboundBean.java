package com.ugs.bean;

/**
 * Created by hanxiaodi on 18/6/14.
 */
public class CrossApplyInboundBean
{
	private String batch_date;
	private String out_batch_no;
	private String issued_type;
	private String file_digest;
	private String file_password;
	private String encodeString;

	public String getBatch_date()
	{
		return batch_date;
	}

	public void setBatch_date(String batch_date)
	{
		this.batch_date = batch_date;
	}

	public String getOut_batch_no()
	{
		return out_batch_no;
	}

	public void setOut_batch_no(String out_batch_no)
	{
		this.out_batch_no = out_batch_no;
	}

	public String getIssued_type()
	{
		return issued_type;
	}

	public void setIssued_type(String issued_type)
	{
		this.issued_type = issued_type;
	}

	public String getFile_digest()
	{
		return file_digest;
	}

	public void setFile_digest(String file_digest)
	{
		this.file_digest = file_digest;
	}

	public String getFile_password()
	{
		return file_password;
	}

	public void setFile_password(String file_password)
	{
		this.file_password = file_password;
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

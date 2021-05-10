package com.common;

import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by hanxiaodi on 18/6/29.
 */
public class HttpRequester
{
	static Logger logger = LoggerFactory.getLogger(HttpRequester.class);
	static final char NAME_VALUE_SEPARATOR ='=';
	static final char URL_PARAM_SEPARATOR ='?';
	static final char QP_SEP_A ='&';

	String url;
	Map<String,String> params;
	Charset charset =Charset.forName( "UTF-8" );
	boolean urlEncoded =true;
	public HttpRequester(String url,Map<String,String> params){
		this.url=url;
		this.params=params;
	}

	public HttpRequester urlEncoded(boolean urlEncoded){
		this.urlEncoded=urlEncoded;
		return this;
	}

//	public HttpResult post(){
//		HttpPost request = new HttpPost( url );
//		request.setEntity();
//	}


}

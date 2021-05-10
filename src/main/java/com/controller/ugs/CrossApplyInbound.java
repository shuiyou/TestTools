package com.controller.ugs;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSON;
import com.ugs.bean.CrossApplyInboundBean;
import com.ugs.bean.UgsBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanxiaodi on 18/6/14.
 */
@Controller
public class CrossApplyInbound extends HttpServlet
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CrossApplyInbound") public void doPost(@ModelAttribute("ugsBaseBean") UgsBaseBean ugsBaseBean,
			@ModelAttribute("caidBean") CrossApplyInboundBean caidBean, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		request.setCharacterEncoding( "UTF-8" );
		response.setCharacterEncoding( "UTF-8" );
		ugsBaseBean.set_input_charset( request.getParameter( "_input_charset" ) );

		StringBuilder inputString = new StringBuilder( "" );
		Map<String, String> PA = new HashMap<String, String>();
		if (!ugsBaseBean.getService().isEmpty())
		{
			inputString.append( "service=" + ugsBaseBean.getService() );
			PA.put( "service", ugsBaseBean.getService() );
		}
		if (!ugsBaseBean.getVersion().isEmpty())
		{
			inputString.append( ",version=" + ugsBaseBean.getVersion() );
			PA.put( "version", ugsBaseBean.getVersion() );
		}
		if (!ugsBaseBean.getRequest_time().isEmpty())
		{
			inputString.append( ",request_time=" + ugsBaseBean.getRequest_time() );
			PA.put( "request_time", ugsBaseBean.getRequest_time() );
		}
		if (!ugsBaseBean.getPartner_id().isEmpty())
		{
			inputString.append( ",partner_id=" + ugsBaseBean.getPartner_id() );
			PA.put( "partner_id", ugsBaseBean.getPartner_id() );
		}
		if (!ugsBaseBean.get_input_charset().isEmpty())
		{
			inputString.append( ",_input_charset=" + ugsBaseBean.get_input_charset() );
			PA.put( "_input_charset", ugsBaseBean.get_input_charset() );
		}
		if (!ugsBaseBean.getSign_type().isEmpty())
		{
			inputString.append( ",sign_type=" + ugsBaseBean.getSign_type() );
			PA.put( "sign_type", ugsBaseBean.getSign_type() );
		}
		if (!ugsBaseBean.getSign_version().isEmpty())
		{
			inputString.append( ",sign_version=" + ugsBaseBean.getSign_version() );
			PA.put( "sign_version", ugsBaseBean.getSign_version() );
		}
		if (!ugsBaseBean.getEncrypt_version().isEmpty())
		{
			inputString.append( ",encrypt_version=" + ugsBaseBean.getEncrypt_version() );
			PA.put( "encrypt_version", ugsBaseBean.getEncrypt_version() );
		}
		if (!ugsBaseBean.getNotify_url().isEmpty())
		{
			inputString.append( ",notify_url=" + ugsBaseBean.getNotify_url() );
			PA.put( "notify_url", ugsBaseBean.getNotify_url() );
		}
		if (!ugsBaseBean.getReturn_url().isEmpty())
		{
			inputString.append( ",return_url=" + ugsBaseBean.getReturn_url() );
			PA.put( "return_url", ugsBaseBean.getReturn_url() );
		}

		//定义业务参数
		StringBuilder paramContext = new StringBuilder( "" );
		if (!caidBean.getBatch_date().isEmpty())
		{
			paramContext.append( "\"batch_date\":\"" + caidBean.getBatch_date() + "\"" );
		}

		if (!caidBean.getOut_batch_no().isEmpty())
		{
			paramContext.append( ",\"out_batch_no\":\"" + caidBean.getOut_batch_no() + "\"" );
		}
		if (!caidBean.getIssued_type().isEmpty())
		{
			paramContext.append( ",\"issued_type\":\"" + caidBean.getIssued_type() + "\"" );
		}

		if (!caidBean.getFile_digest().isEmpty())
		{
			paramContext.append( ",\"file_digest\":\"" + caidBean.getFile_digest() + "\"" );
		}

		if (!caidBean.getFile_password().isEmpty())
		{
			paramContext.append( ",\"file_password\":\"" + caidBean.getFile_password() + "\"" );
		}

		CrossApplyInboundBean crossaplyinboundReq = new CrossApplyInboundBean();
		crossaplyinboundReq.setBatch_date( caidBean.getBatch_date() );
		crossaplyinboundReq.setOut_batch_no( caidBean.getOut_batch_no() );
		crossaplyinboundReq.setIssued_type( caidBean.getIssued_type() );
		crossaplyinboundReq.setFile_digest( caidBean.getFile_digest() );
		crossaplyinboundReq.setFile_password( caidBean.getFile_password() );

		Tools tool = new Tools();
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();

		PA.put( "biz_content" ,JSON.toJSONString( crossaplyinboundReq));

		String[] bizsplitString = JSON.toJSONString(crossaplyinboundReq).replace( "{","" ).replace( "}","" ).split( "," );
		String bizsortString = tool.sortStringWithSeparator( bizsplitString, "," );
		bizsortString = bizsortString.replace( ",", "replaceFlag" );
		System.out.println( bizsortString );

		inputString.append( ",biz_content={" + bizsortString + "}" );
		String[] splitString = inputString.toString().split( "," );

		String sortString = tool.sortStringWithSeparator( splitString, "&" );
		String signString = tool.removeFromString( sortString, "&", "&", "startswith", "sign" )
				.replaceAll( "replaceFlag", "," );
		//	signString = signString.replace( "@","," );
		System.out.println( "验证签名串:"+signString);

		String sign = null;

		if (ugsBaseBean.getSign_type().equals( "RSA" ))
		{
			rsa.setPrivateKey( ugsBaseBean.getRsakey() );
			try
			{
				sign = rsa.sign( signString );
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PA.put( "sign", sign );

		if (ugsBaseBean.getSign_type().equals( "MD5" ))
		{
			String md5SignString = signString + ugsBaseBean.getMd5key();
			sign = md5.getMD5( md5SignString, "UTF-8" );
		}

		caidBean.setEncodeString( sortString );

		if (!ugsBaseBean.getNotify_url().isEmpty())
		{
			caidBean.setEncodeString(
					tool.removeFromString( caidBean.getEncodeString(), "&", "&", "startswith", "notify_url" ) + "&notify_url="
							+ tool.textEncode( ugsBaseBean.getNotify_url(), "UTF-8" ) );
		}
		if (!ugsBaseBean.getReturn_url().isEmpty())
		{
			caidBean.setEncodeString(
					tool.removeFromString( caidBean.getEncodeString(), "&", "&", "startswith", "return_url" ) + "&return_url="
							+ tool.textEncode( ugsBaseBean.getReturn_url(), "UTF-8" ) );
		}

		String requestString = caidBean.getEncodeString().replaceAll( "replaceFlag", "," ) + "&sign=" + sign;
		//	requestString = requestString.replace( "@","," );
		System.out.println( "请求报文: " + requestString );

		String responseString = tool.textDncode( tool.postN( ugsBaseBean.getUrl(), PA ), "UTF-8" );
		System.out.println( responseString );

		//获取表单
		if (responseString.contains( "</form>" ))
		{
			int i = responseString.indexOf( "<form" );
			int j = responseString.indexOf( "\",\"response_code" );
			String formdata = responseString.substring( i, j );
			System.out.println( "截取的内容:" + formdata );
			request.setAttribute( "response", formdata );
			request.setAttribute( "request", requestString );
			request.getRequestDispatcher( "formdataresult.jsp" ).forward( request, response );
		}
		else
		{
			request.setAttribute( "response", responseString );
			request.setAttribute( "request", requestString );
			request.getRequestDispatcher( "result.jsp" ).forward( request, response );
		}

		//如果返回的是表单形式用下面来自动提交表单
		//	request.setAttribute("request", requestString);
		//	request.getRequestDispatcher("result.jsp").forward(request, response);
		//	request.getRequestDispatcher("formdataresult.jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		this.doPost( request, response );
	}
}

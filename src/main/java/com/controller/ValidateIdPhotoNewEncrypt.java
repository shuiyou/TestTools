package com.controller;

import com.common.MD5Util;
import com.common.OrderBasicNameValuePair;
import com.common.RSAUtil;
import com.common.SignData;
import com.pas.bean.PasBaseBean;
import com.pas.bean.ValidateIdPhotoNewEncryptBean;
import com.weihui.gateway.sign.RSA;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hanxiaodi on 18/5/3.
 */
@Controller
public class ValidateIdPhotoNewEncrypt extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "ValidateIdPhotoNewEncrypt")
	public void doPost(@ModelAttribute("pasBaseBean") PasBaseBean pasBaseBean,
			@ModelAttribute("vidneBean") ValidateIdPhotoNewEncryptBean vidneBean,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pasBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");

		List<OrderBasicNameValuePair> formparams = new ArrayList<OrderBasicNameValuePair>();
		if (!pasBaseBean.getService().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("service", pasBaseBean.getService()));
		}
		if (!pasBaseBean.getVersion().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("version", pasBaseBean.getVersion()));
		}
		if (!pasBaseBean.getRequest_time().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("request_time", pasBaseBean.getRequest_time()));
		}
		if (!pasBaseBean.getPartner_id().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("partner_id", pasBaseBean.getPartner_id()));
		}
		if (!pasBaseBean.get_input_charset().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("_input_charset", pasBaseBean.get_input_charset()));
		}
		if (!pasBaseBean.getSign_type().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("sign_type", pasBaseBean.getSign_type()));
		}
		if (!pasBaseBean.getSign_version().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("sign_version", pasBaseBean.getSign_version()));
		}
		if (!pasBaseBean.getEncrypt_version().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("encrypt_version", pasBaseBean.getEncrypt_version()));
		}

		if (!pasBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + pasBaseBean.getMemo());
		}
		if (!vidneBean.getOut_request_no().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("out_request_no", vidneBean.getOut_request_no()));
		}

		if (!vidneBean.getExtend_param().isEmpty()) {
			formparams.add(new OrderBasicNameValuePair("extend_param" , vidneBean.getExtend_param()));
		}
		if(!vidneBean.getCert_no().isEmpty()){
			String certnoEncrypt = rsa.encrypt(vidneBean.getCert_no(),pasBaseBean.getEncryptkey());
			formparams.add( new OrderBasicNameValuePair( "cert_no",certnoEncrypt));
		}
		if(!vidneBean.getCert_type().isEmpty()){
			formparams.add( new OrderBasicNameValuePair( "cert_type",vidneBean.getCert_type()));
		}
		if(!vidneBean.getReal_name().isEmpty()){
			String realnameEncrypt = rsa.encrypt( vidneBean.getReal_name(),pasBaseBean.getEncryptkey());
			formparams.add( new OrderBasicNameValuePair( "real_name",realnameEncrypt));
		}

		//if (!vbcBean.getPic_file().isEmpty()) {
		formparams.add(new OrderBasicNameValuePair("pic_file", vidneBean.getPic_file()));

		formparams.add(new OrderBasicNameValuePair("pic_file2", vidneBean.getPic_file2()));
		//}


		SignData data = new SignData();
		data.setCharset(pasBaseBean.get_input_charset());
		String signSrc = getSignStr(formparams);
		// signSrc =
		// "_input_charset=UTF-8&is_success=T&memo=sunjie_test&partner_id=200003670082&response_time=20140611012959";
		System.out.println("signSrc:" + signSrc);
		data.setSignSrc(signSrc);
		data.setSignType(pasBaseBean.getSign_type());
		String sign = null;
		if (pasBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(pasBaseBean.getRsakey());
			try {
				sign = RSA.sign(data.getSignSrc(), pasBaseBean.getRsakey(), data.getCharset());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		formparams.add(new OrderBasicNameValuePair("sign", sign));

		List<OrderBasicNameValuePair> formparams1 = convert(formparams, pasBaseBean.get_input_charset());

		HttpPost postMethod = new HttpPost(pasBaseBean.getUrl());
		// 构建entity
		HttpEntity entity = new UrlEncodedFormEntity(formparams1, pasBaseBean.get_input_charset());
		postMethod.setEntity(entity);
		String requestString = EntityUtils.toString( entity );



		System.out.println("请求报文: " + requestString);

		String responseString = (String) new DefaultHttpClient().execute(postMethod, getHandler(pasBaseBean.get_input_charset()));
		responseString = java.net.URLDecoder.decode(responseString, "UTF-8");
		System.out.println(responseString);
		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doPost(request, response);
	}

	public ResponseHandler getHandler(final String charset) {
		ResponseHandler<String> handler = new ResponseHandler<String>() {
			public String handleResponse(HttpResponse response) throws ClientProtocolException,
					IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return new String(EntityUtils.toByteArray(entity), charset);
				} else {
					return null;
				}
			}
		};
		return handler;
	}


	private String getSignStr(List<OrderBasicNameValuePair> list) {
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for (OrderBasicNameValuePair item : list) {
			if (item.getName().equals("sign") || item.getName().equals("sign_type")
					|| item.getName().equals("sign_version")) {
				continue;
			}
			if (StringUtils.isNotEmpty(item.getValue())) {
				if (!first) {
					sb.append("&");
				}

				sb.append(item.getName()).append("=").append(item.getValue());

				first = false;
			}
		}
		return sb.toString();
	}

	public List<OrderBasicNameValuePair> convert(List<OrderBasicNameValuePair> older, String charset)
			throws UnsupportedEncodingException
	{
		List<OrderBasicNameValuePair> newList = new ArrayList<OrderBasicNameValuePair>();
		for (OrderBasicNameValuePair item : older) {
			String str = item.getValue();
			if (!StringUtils.isEmpty(str)) {
				String encode = URLEncoder.encode(str, charset);
				OrderBasicNameValuePair newItem = new OrderBasicNameValuePair(item.getName(),
						encode);
				newList.add(newItem);
			} else {
				newList.add(item);
			}

		}
		return newList;
	}
}

package com.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.mgs.bean.MgsBaseBean;
import com.mgs.bean.ShowMemberInfosSinaBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class ShowMemberInfosSina extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "ShowMemberInfosSina")
	public void doPost1(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("smisBean") ShowMemberInfosSinaBean smisBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		mgsBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!mgsBaseBean.getService().isEmpty()) {
			inputString.append("service=" + mgsBaseBean.getService());
		}
		if (!mgsBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + mgsBaseBean.getVersion());
		}
		if (!mgsBaseBean.getRequest_time().isEmpty()) {
			inputString
					.append(",request_time=" + mgsBaseBean.getRequest_time());
		}
		if (!mgsBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + mgsBaseBean.getPartner_id());
		}
		if (!mgsBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ mgsBaseBean.get_input_charset());
		}
		if (!mgsBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + mgsBaseBean.getSign_type());
		}
		if (!mgsBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + mgsBaseBean.getSign_version());
		}
		if (!mgsBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ mgsBaseBean.getEncrypt_version());
		}
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + mgsBaseBean.getNotify_url());
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + mgsBaseBean.getReturn_url());
		}
		if (!mgsBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + mgsBaseBean.getMemo());
		}

		if (!smisBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + smisBean.getIdentity_id());
		}
		if (!smisBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + smisBean.getIdentity_type());
		}
		if (!smisBean.getResp_method().isEmpty()) {
			inputString.append(",resp_method=" + smisBean.getResp_method());
		}
		if (!smisBean.getDefault_page().isEmpty()) {
			inputString.append(",default_page=" + smisBean.getDefault_page());
		}
		if (!smisBean.getHide_pages().isEmpty()) {
			inputString.append(",hide_pages=" + smisBean.getHide_pages());
		}
		if (!smisBean.gettemplet_custom().isEmpty()) {
			inputString.append(",templet_custom=" + smisBean.gettemplet_custom());
		}
		if (!smisBean.getSingle_custom().isEmpty() && smisBean.getSingle_custom().contains(",")) {
			inputString.append(",single_custom=" + smisBean.getSingle_custom().replace(",", "#"));
		}else if (!smisBean.getSingle_custom().isEmpty() && !smisBean.getSingle_custom().contains(",")){
			inputString.append(",single_custom=" + smisBean.getSingle_custom());
		}
		if (!mgsBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",cashdesk_addr_category=" + mgsBaseBean.getCashdesk_addr_category());
		}
		if (!smisBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + smisBean.getExtend_param());
		}

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replace("#", ",");

		String sign = null;
		if (mgsBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(mgsBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (mgsBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + mgsBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		smisBean.setEncodeString(sortString);
		
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			smisBean.setEncodeString(tool.removeFromString(
					smisBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			smisBean.setEncodeString(tool.removeFromString(
					smisBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("service", mgsBaseBean.getService());
		params.put("version", mgsBaseBean.getVersion());
		params.put("request_time", mgsBaseBean.getRequest_time());
		params.put("partner_id", mgsBaseBean.getPartner_id());
		params.put("_input_charset", mgsBaseBean.get_input_charset());
		params.put("sign_type", mgsBaseBean.getSign_type());
		params.put("sign_version", mgsBaseBean.getSign_version());
		params.put("encrypt_version", mgsBaseBean.getEncrypt_version());
		params.put("notify_url", mgsBaseBean.getNotify_url());
		params.put("return_url", mgsBaseBean.getReturn_url());
		params.put("cashdesk_addr_category", mgsBaseBean.getCashdesk_addr_category());
		if (!mgsBaseBean.getMemo().isEmpty()) {
			params.put("memo", mgsBaseBean.getMemo());
		}
		params.put("identity_id", smisBean.getIdentity_id());
		params.put("identity_type", smisBean.getIdentity_type());
		if (!smisBean.getResp_method().isEmpty()) {
			params.put("resp_method", smisBean.getResp_method());
		}
		if (!smisBean.getDefault_page().isEmpty()) {
			params.put("default_page", smisBean.getDefault_page());
		}
		if (!smisBean.getHide_pages().isEmpty()) {
			params.put("hide_pages", smisBean.getHide_pages());
		}
		if (!smisBean.gettemplet_custom().isEmpty()) {
			params.put("templet_custom", smisBean.gettemplet_custom());
		}
		if (!smisBean.getSingle_custom().isEmpty()) {
			params.put("single_custom", URLEncoder.encode(smisBean.getSingle_custom(), "UTF-8"));
		}
		if (!smisBean.getExtend_param().isEmpty()) {
			params.put("extend_param", smisBean.getExtend_param());
		}
		params.put("sign", tool.textEncode(sign, "UTF-8"));
		
		String resp = tool.getHtmlData(tool.getFormData(params), mgsBaseBean.getUrl());
		
		
		
		
//		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//		HttpResponse postResponse=new DefaultHttpClient().execute(httpPost);
//		HttpEntity entity = ((HttpResponse)postResponse).getEntity();
//		String result = EntityUtils.toString(entity);
//		System.out.println(result);
		

//		String requestString = smisBean.getEncodeString() + "&sign="
//				+ tool.textEncode(sign, "UTF-8");
//		System.out.println("请求报文: " + requestString);
//
//		String responseString = tool.textDncode(
//				tool.post(mgsBaseBean.getUrl(), requestString), "UTF-8");
//		System.out.println(responseString);
//
//		request.setAttribute("response", responseString);
		request.setAttribute("request", resp);
		request.getRequestDispatcher("submitForm.jsp").forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doPost(request, response);
	}

}

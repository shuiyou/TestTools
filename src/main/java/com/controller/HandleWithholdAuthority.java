package com.controller;

import java.io.IOException;

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
import com.mgs.bean.HandleWithholdAuthorityBean;
import com.mgs.bean.MgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class HandleWithholdAuthority extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "HandleWithholdAuthority")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("hwaBean") HandleWithholdAuthorityBean hwaBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		if (!hwaBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + hwaBean.getIdentity_id());
		}
		if (!hwaBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + hwaBean.getIdentity_type());
		}
		if (!hwaBean.getQuota().isEmpty() && hwaBean.getQuota().contains(",")) {
			inputString.append(",quota=" + hwaBean.getQuota().replace(",", "#"));
		}else if (!hwaBean.getQuota().isEmpty() && !hwaBean.getQuota().contains(",")){
			inputString.append(",quota=" + hwaBean.getQuota());
		}
		if (!hwaBean.getDay_quota().isEmpty() && hwaBean.getDay_quota().contains(",")) {
			inputString.append(",day_quota=" + hwaBean.getDay_quota().replace(",", "#"));
		}else if (!hwaBean.getDay_quota().isEmpty() && !hwaBean.getDay_quota().contains(",")){
			inputString.append(",day_quota=" + hwaBean.getDay_quota());
		}
		if (!mgsBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",cashdesk_addr_category=" + mgsBaseBean.getCashdesk_addr_category());
		}
		
		if (!hwaBean.getAuth_type_whitelist().isEmpty() && hwaBean.getAuth_type_whitelist().contains(",")) {
			inputString.append(",auth_type_whitelist=" + hwaBean.getAuth_type_whitelist().replace(",", "#"));
		} else if (!hwaBean.getAuth_type_whitelist().isEmpty() && !hwaBean.getAuth_type_whitelist().contains(",")) {
			inputString.append(",auth_type_whitelist=" + hwaBean.getAuth_type_whitelist());
		}
		if (!hwaBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + hwaBean.getExtend_param());
		}

		System.out.println(inputString.toString());

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replace("#", ",");
		

		String sign = null;
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();

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
        
		hwaBean.setEncodeString(sortString);
		if (!hwaBean.getAuth_type_whitelist().isEmpty()) {
			hwaBean.setEncodeString(tool.removeFromString(
					hwaBean.getEncodeString(), "&", "&", "startswith",
					"auth_type_whitelist")
					+ "&auth_type_whitelist="
					+ tool.textEncode(hwaBean.getAuth_type_whitelist(), "UTF-8"));
		}
		if (!hwaBean.getQuota().isEmpty()) {
			hwaBean.setEncodeString(tool.removeFromString(
					hwaBean.getEncodeString(), "&", "&", "startswith",
					"quota")
					+ "&quota="
					+ tool.textEncode(hwaBean.getQuota(), "UTF-8"));
		}
		if (!hwaBean.getDay_quota().isEmpty()) {
			hwaBean.setEncodeString(tool.removeFromString(
					hwaBean.getEncodeString(), "&", "&", "startswith",
					"day_quota")
					+ "&day_quota="
					+ tool.textEncode(hwaBean.getDay_quota(), "UTF-8"));
		}
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			hwaBean.setEncodeString(tool.removeFromString(
					hwaBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			hwaBean.setEncodeString(tool.removeFromString(
					hwaBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = hwaBean.getEncodeString() + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(mgsBaseBean.getUrl(), requestString), "UTF-8");
		System.out.println(responseString);

		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doPost(request, response);
	}

}

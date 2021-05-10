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
import com.mgs.bean.SmtFundAgentBuyBean;
import com.mgs.bean.MgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class SmtFundAgentBuy extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "SmtFundAgentBuy")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("sfabBean") SmtFundAgentBuyBean sfabBean,
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
		if (!sfabBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + sfabBean.getIdentity_id());
		}
		if (!sfabBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + sfabBean.getIdentity_type());
		}
		if (!sfabBean.getClient_ip().isEmpty() && sfabBean.getClient_ip().contains(",")) {
			inputString.append(",client_ip=" + sfabBean.getClient_ip().replace(",", "#"));
		}else if (!sfabBean.getClient_ip().isEmpty() && !sfabBean.getClient_ip().contains(",")){
			inputString.append(",client_ip=" + sfabBean.getClient_ip());
		}
		String encryptAgentName = null;
		if (!sfabBean.getAgent_name().isEmpty()) {
			try {
				encryptAgentName = rsa.encrypt(sfabBean.getAgent_name(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",agent_name=" + encryptAgentName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptLicenseNo = null;
		if (!sfabBean.getLicense_no().isEmpty()) {
			try {
				encryptLicenseNo = rsa.encrypt(sfabBean.getLicense_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",license_no=" + encryptLicenseNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptAgentMobile = null;
		if (!sfabBean.getAgent_mobile().isEmpty()) {
			try {
				encryptAgentMobile = rsa.encrypt(sfabBean.getAgent_mobile(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",agent_mobile=" + encryptAgentMobile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!sfabBean.getEmail().isEmpty()) {
			inputString.append(",email=" + sfabBean.getEmail());
		}

		System.out.println(inputString.toString());

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign");

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

		sfabBean.setEncodeString(sortString);
		if (encryptAgentName != null) {
			sfabBean.setEncodeString(tool.removeFromString(
					sfabBean.getEncodeString(), "&", "&", "startswith",
					"agent_name")
					+ "&agent_name="
					+ tool.textEncode(encryptAgentName, "UTF-8"));
		}
		if (encryptLicenseNo != null) {
			sfabBean.setEncodeString(tool.removeFromString(
					sfabBean.getEncodeString(), "&", "&", "startswith",
					"license_no")
					+ "&license_no="
					+ tool.textEncode(encryptLicenseNo, "UTF-8"));
		}
		if (encryptAgentMobile != null) {
			sfabBean.setEncodeString(tool.removeFromString(
					sfabBean.getEncodeString(), "&", "&", "startswith",
					"agent_mobile")
					+ "&agent_mobile="
					+ tool.textEncode(encryptAgentMobile, "UTF-8"));
		}
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			sfabBean.setEncodeString(tool.removeFromString(
					sfabBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			sfabBean.setEncodeString(tool.removeFromString(
					sfabBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = sfabBean.getEncodeString() + "&sign="
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

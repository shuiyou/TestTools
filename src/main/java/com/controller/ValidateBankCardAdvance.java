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
import com.pas.bean.ValidateBankCardAdvanceBean;
import com.pas.bean.PasBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class ValidateBankCardAdvance extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "ValidateBankCardAdvance")
	public void doPost(
			@ModelAttribute("validateBankCardAdvanceBean") ValidateBankCardAdvanceBean validateBankCardAdvanceBean,
			@ModelAttribute("pasBaseBean") PasBaseBean pasBaseBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pasBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!pasBaseBean.getService().isEmpty()) {
			inputString.append("service=" + pasBaseBean.getService());
		}
		if (!pasBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + pasBaseBean.getVersion());
		}
		if (!pasBaseBean.getRequest_time().isEmpty()) {
			inputString
					.append(",request_time=" + pasBaseBean.getRequest_time());
		}
		if (!pasBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + pasBaseBean.getPartner_id());
		}
		if (!pasBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ pasBaseBean.get_input_charset());
		}
		if (!pasBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + pasBaseBean.getSign_type());
		}
		if (!pasBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + pasBaseBean.getSign_version());
		}
		if (!pasBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ pasBaseBean.getEncrypt_version());
		}
		if (!pasBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + pasBaseBean.getNotify_url());
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + pasBaseBean.getReturn_url());
		}
		if (!pasBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + pasBaseBean.getMemo());
		}
		
		if (!validateBankCardAdvanceBean.getTicket().isEmpty()) {
			inputString.append(",ticket=" + validateBankCardAdvanceBean.getTicket());
		}
		if (!validateBankCardAdvanceBean.getValid_code().isEmpty()) {
			inputString.append(",valid_code=" + validateBankCardAdvanceBean.getValid_code());
		}
		if (!validateBankCardAdvanceBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + validateBankCardAdvanceBean.getExtend_param());
		}

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replaceAll("replaceFlag", ",");
		System.out.println(signString);

		String sign = null;
		
		if (pasBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(pasBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (pasBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + pasBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		validateBankCardAdvanceBean.setEncodeString(sortString);
		
		if (!validateBankCardAdvanceBean.getTicket().isEmpty()) {
			validateBankCardAdvanceBean.setEncodeString(tool.removeFromString(
					validateBankCardAdvanceBean.getEncodeString(), "&", "&", "startswith",
					"Ticket")
					+ "&Ticket="
					+ tool.textEncode(validateBankCardAdvanceBean.getTicket(), "UTF-8"));
		}
		if (!validateBankCardAdvanceBean.getValid_code().isEmpty()) {
			validateBankCardAdvanceBean.setEncodeString(tool.removeFromString(
					validateBankCardAdvanceBean.getEncodeString(), "&", "&", "startswith",
					"Valid_code")
					+ "&Valid_code="
					+ tool.textEncode(validateBankCardAdvanceBean.getValid_code(), "UTF-8"));
		}
		if (!validateBankCardAdvanceBean.getExtend_param().isEmpty()) {
			validateBankCardAdvanceBean.setEncodeString(tool.removeFromString(
					validateBankCardAdvanceBean.getEncodeString(), "&", "&", "startswith",
					"Extend_param")
					+ "&Extend_param="
					+ tool.textEncode(validateBankCardAdvanceBean.getExtend_param(), "UTF-8"));
		}
		
		if (!pasBaseBean.getNotify_url().isEmpty()) {
			validateBankCardAdvanceBean.setEncodeString(tool.removeFromString(
					validateBankCardAdvanceBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(pasBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			validateBankCardAdvanceBean.setEncodeString(tool.removeFromString(
					validateBankCardAdvanceBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(pasBaseBean.getReturn_url(), "UTF-8"));
		}
		


		String requestString = validateBankCardAdvanceBean.getEncodeString().replaceAll(
				"replaceFlag", ",")
				+ "&sign=" + tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);
		
//		String requestString = validateBankCardAdvanceBean.getEncodeString() + "&sign="
//				+ tool.textEncode(sign, "UTF-8");
//		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(pasBaseBean.getUrl(), requestString), "UTF-8");
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

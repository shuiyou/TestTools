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
import com.pas.bean.AuthIdentityBean;
import com.pas.bean.PasBaseBean;

/**
 * 
 * @author zjc
 *
 */
@Controller
public class AuthIdentityPas extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "AuthIdentityPas")
	public void doPost(@ModelAttribute("pasBaseBean") PasBaseBean pasBaseBean,
			@ModelAttribute("authiBean") AuthIdentityBean authiBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//System.out.println("ttt----"+authiBean.getAuth_type());
		
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
		if (!authiBean.getOut_request_no().isEmpty()) {
			inputString.append(",out_request_no=" + authiBean.getOut_request_no());
		}
		
		if (!authiBean.getAuth_type().isEmpty()) {
			inputString.append(",auth_type=" + authiBean.getAuth_type());
		}
		if (!authiBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + authiBean.getExtend_param());
		}

		if (!authiBean.getCert_type().isEmpty()) {
			inputString.append(",cert_type=" + authiBean.getCert_type());
		}
			
		String encryptBankAccountNo = null;
		if (!authiBean.getBank_account_no().isEmpty()) {
			try {
				encryptBankAccountNo = rsa.encrypt(authiBean.getBank_account_no(),
						pasBaseBean.getEncryptkey());
				inputString.append(",bank_account_no=" + encryptBankAccountNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		String encryptAccountName = null;
		if (!authiBean.getAccount_name().isEmpty()) {
			try {
				encryptAccountName = rsa.encrypt(authiBean.getAccount_name(),
						pasBaseBean.getEncryptkey());
				inputString.append(",account_name=" + encryptAccountName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		String encryptCertNo = null;
		if (!authiBean.getCert_no().isEmpty()) {
			try {
				encryptCertNo = rsa.encrypt(authiBean.getCert_no(),
						pasBaseBean.getEncryptkey());
				inputString.append(",cert_no=" + encryptCertNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		String encryptPhoneNo = null;
		if (!authiBean.getPhone_no().isEmpty()) {
			try {
				encryptPhoneNo = rsa.encrypt(authiBean.getPhone_no(),
						pasBaseBean.getEncryptkey());
				inputString.append(",phone_no=" + encryptPhoneNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign");

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
		
		authiBean.setEncodeString(sortString);
	    if (encryptBankAccountNo != null) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"bank_account_no")
					+ "&bank_account_no="
					+ tool.textEncode(encryptBankAccountNo, "UTF-8"));
		}
		
		if (encryptAccountName != null) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"account_name")
					+ "&account_name="
					+ tool.textEncode(encryptAccountName, "UTF-8"));
		}

		if (encryptCertNo != null) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"cert_no")
					+ "&cert_no="
					+ tool.textEncode(encryptCertNo, "UTF-8"));
		}
		if (encryptPhoneNo != null) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"phone_no")
					+ "&phone_no="
					+ tool.textEncode(encryptPhoneNo, "UTF-8"));
		}
		

		if (!pasBaseBean.getNotify_url().isEmpty()) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(pasBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			authiBean.setEncodeString(tool.removeFromString(
					authiBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(pasBaseBean.getReturn_url(), "UTF-8"));
		}
		
		
		String requestString = authiBean.getEncodeString() + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

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

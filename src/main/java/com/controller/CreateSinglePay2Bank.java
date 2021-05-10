package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2c.bean.B2CBaseBean;
import com.b2c.bean.CreateSinglePay2BankBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateSinglePay2Bank extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateSinglePay2Bank")
	public void doPost(@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
			@ModelAttribute("csp2bBean") CreateSinglePay2BankBean csp2bBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		b2cBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!b2cBaseBean.getService().isEmpty()) {
			inputString.append("service=" + b2cBaseBean.getService());
		}
		if (!b2cBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + b2cBaseBean.getVersion());
		}
		if (!b2cBaseBean.getRequest_time().isEmpty()) {
			inputString
					.append(",request_time=" + b2cBaseBean.getRequest_time());
		}
		if (!b2cBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + b2cBaseBean.getPartner_id());
		}
		if (!b2cBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ b2cBaseBean.get_input_charset());
		}
		if (!b2cBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + b2cBaseBean.getSign_type());
		}
		if (!b2cBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + b2cBaseBean.getSign_version());
		}
		if (!b2cBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ b2cBaseBean.getEncrypt_version());
		}
		if (!b2cBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + b2cBaseBean.getNotify_url());
		}
		if (!b2cBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + b2cBaseBean.getReturn_url());
		}
		if (!b2cBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + b2cBaseBean.getMemo());
		}
		if (!csp2bBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + csp2bBean.getOut_trade_no());
		}
		String encryptRealName = null;
		if (!csp2bBean.getReal_name().isEmpty()) {
			try {
				encryptRealName = rsa.encrypt(csp2bBean.getReal_name(),
						b2cBaseBean.getEncryptkey());
				inputString.append(",real_name=" + encryptRealName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String encryptCertNum = null;
		if (!csp2bBean.getCert_num().isEmpty()) {
			try {
				encryptCertNum = rsa.encrypt(csp2bBean.getCert_num(),
						b2cBaseBean.getEncryptkey());
				inputString.append(",cert_num=" + encryptCertNum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String encryptBankAccountNum = null;
		if (!csp2bBean.getBank_account_num().isEmpty()) {
			try {
				encryptBankAccountNum = rsa.encrypt(csp2bBean.getBank_account_num(),
						b2cBaseBean.getEncryptkey());
				inputString.append(",bank_account_num=" + encryptBankAccountNum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!csp2bBean.getBank_name().isEmpty()) {
			inputString.append(",bank_name=" + csp2bBean.getBank_name());
		}
		if (!csp2bBean.getBank_code().isEmpty()) {
			inputString.append(",bank_code=" + csp2bBean.getBank_code());
		}
		if (!csp2bBean.getProvince().isEmpty()) {
			inputString.append(",province=" + csp2bBean.getProvince());
		}
		if (!csp2bBean.getCity().isEmpty()) {
			inputString.append(",city=" + csp2bBean.getCity());
		}
		if (!csp2bBean.getBank_branch().isEmpty()) {
			inputString.append(",bank_branch=" + csp2bBean.getBank_branch());
		}
		if (!csp2bBean.getAmount().isEmpty()) {
			inputString.append(",amount=" + csp2bBean.getAmount());
		}
		if (!csp2bBean.getCard_attribute().isEmpty()) {
			inputString
					.append(",card_attribute=" + csp2bBean.getCard_attribute());
		}
		if (!csp2bBean.getCard_type().isEmpty()) {
			inputString.append(",card_type=" + csp2bBean.getCard_type());
		}
		if (!csp2bBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + csp2bBean.getSummary());
		}
		if (!csp2bBean.getPayto_type().isEmpty()) {
			inputString.append(",payto_type=" + csp2bBean.getPayto_type());
		}
		if (!csp2bBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + csp2bBean.getExtend_param());
		}

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign");

		String sign = null;
		if (b2cBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(b2cBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (b2cBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + b2cBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		csp2bBean.setEncodeString(sortString);
		if (encryptRealName != null) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"real_name")
					+ "&real_name="
					+ tool.textEncode(encryptRealName, "UTF-8"));
		}
		if (encryptCertNum != null) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"cert_num")
					+ "&cert_num="
					+ tool.textEncode(encryptCertNum, "UTF-8"));
		}
		if (encryptBankAccountNum != null) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"bank_account_num")
					+ "&bank_account_num="
					+ tool.textEncode(encryptBankAccountNum, "UTF-8"));
		}
		if (!csp2bBean.getBank_name().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"bank_name")
					+ "&bank_name="
					+ tool.textEncode(csp2bBean.getBank_name(), "UTF-8"));
		}
		if (!csp2bBean.getProvince().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"province")
					+ "&province="
					+ tool.textEncode(csp2bBean.getProvince(), "UTF-8"));
		}
		if (!csp2bBean.getCity().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"city")
					+ "&city="
					+ tool.textEncode(csp2bBean.getCity(), "UTF-8"));
		}
		if (!csp2bBean.getBank_branch().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"bank_branch")
					+ "&bank_branch="
					+ tool.textEncode(csp2bBean.getBank_branch(), "UTF-8"));
		}
		if (!csp2bBean.getSummary().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(csp2bBean.getSummary(), "UTF-8"));
		}
		if (!csp2bBean.getExtend_param().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"extend_param")
					+ "&extend_param="
					+ tool.textEncode(csp2bBean.getExtend_param(), "UTF-8"));
		}

		if (!b2cBaseBean.getNotify_url().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!b2cBaseBean.getReturn_url().isEmpty()) {
			csp2bBean.setEncodeString(tool.removeFromString(
					csp2bBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = csp2bBean.getEncodeString() + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(b2cBaseBean.getUrl(), requestString), "UTF-8");
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

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
import com.mgs.bean.InitMemberByProcessBean;
import com.mgs.bean.MgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class InitMemberByProcess extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "InitMemberByProcess")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("imbpBean") InitMemberByProcessBean imbpBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		mgsBaseBean.set_input_charset(request.getParameter("_input_charset"));

		String encryptRealName = null;
		String encryptCertNo = null;
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
		
		if (!mgsBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",memo=" + mgsBaseBean.getMemo());
		}
		
		if (!mgsBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",cashdesk_addr_category=" + mgsBaseBean.getCashdesk_addr_category());
		}
		
		if (!imbpBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + imbpBean.getIdentity_id());
		}
		
		
		if (!imbpBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + imbpBean.getIdentity_type());
		}
		if (!imbpBean.getFlow_category().isEmpty()) {
			inputString.append(",flow_category=" + imbpBean.getFlow_category());
		}
		if (!imbpBean.getMember_type().isEmpty()) {
			inputString.append(",member_type=" + imbpBean.getMember_type());
		}
		if (!imbpBean.getReal_name().isEmpty()) {
			try {
				encryptRealName = rsa.encrypt(imbpBean.getReal_name(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",real_name=" + encryptRealName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!imbpBean.getCert_type().isEmpty()) {
			inputString.append(",cert_type=" + imbpBean.getCert_type());
		}
		if (!imbpBean.getCert_no().isEmpty()) {
			try {
				encryptCertNo = rsa.encrypt(imbpBean.getCert_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",cert_no=" + encryptCertNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!imbpBean.getCard_id().isEmpty()) {
			inputString.append(",card_id=" + imbpBean.getCard_id());
		}
		if (!imbpBean.getBank_code().isEmpty()) {
			inputString.append(",bank_code=" + imbpBean.getBank_code());
		}
		String encryptBankAccNo = null;
		if (!imbpBean.getBank_account_no().isEmpty()) {
			try {
				encryptBankAccNo = rsa.encrypt(imbpBean.getBank_account_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",bank_account_no=" + encryptBankAccNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptAccName = null;
		if (!imbpBean.getAccount_name().isEmpty()) {
			try {
				encryptAccName = rsa.encrypt(imbpBean.getAccount_name(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",account_name=" + encryptAccName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!imbpBean.getCard_type().isEmpty()) {
			inputString.append(",card_type=" + imbpBean.getCard_type());
		}
		if (!imbpBean.getCard_attribute().isEmpty()) {
			inputString
					.append(",card_attribute=" + imbpBean.getCard_attribute());
		}
		String encryptPhoneNo = null;
		if (!imbpBean.getPhone_no().isEmpty()) {
			try {
				encryptPhoneNo = rsa.encrypt(imbpBean.getPhone_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",phone_no=" + encryptPhoneNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String encryptValidityPeriod = null;
		if (!imbpBean.getValidity_period().isEmpty()) {
			try {
				encryptValidityPeriod = rsa.encrypt(
						imbpBean.getValidity_period(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",validity_period=" + encryptValidityPeriod);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String encryptCVV2 = null;
		if (!imbpBean.getVerification_value().isEmpty()) {
			try {
				encryptCVV2 = rsa.encrypt(imbpBean.getVerification_value(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",verification_value=" + encryptCVV2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!imbpBean.getProvince().isEmpty()) {
			inputString.append(",province=" + imbpBean.getProvince());
		}
		if (!imbpBean.getCity().isEmpty()) {
			inputString.append(",city=" + imbpBean.getCity());
		}
		if (!imbpBean.getBank_branch().isEmpty()) {
			inputString.append(",bank_branch=" + imbpBean.getBank_branch());
		}
		if (!imbpBean.getWithhold_kind().isEmpty()) {
			inputString.append(",withhold_kind=" + imbpBean.getWithhold_kind());
		}
		if (!imbpBean.getAccount_type().isEmpty()) {
			inputString.append(",account_type=" + imbpBean.getAccount_type());
		}
		if (!imbpBean.getClient_ip().isEmpty() && imbpBean.getClient_ip().contains(",")) {
			inputString.append(",client_ip=" + imbpBean.getClient_ip().replace(",", "#"));
		}else if (!imbpBean.getClient_ip().isEmpty() && !imbpBean.getClient_ip().contains(",")){
			inputString.append(",client_ip=" + imbpBean.getClient_ip());
		}
		
		

		if (!imbpBean.getWithhold_quota().isEmpty() && imbpBean.getWithhold_quota().contains(",")) {
			inputString.append(",withhold_quota=" + imbpBean.getWithhold_quota().replace(",", "#"));
		}else if (!imbpBean.getWithhold_quota().isEmpty() && !imbpBean.getWithhold_quota().contains(",")){
			inputString.append(",withhold_quota=" + imbpBean.getWithhold_quota());
		}
		if (!imbpBean.getWithhold_day_quota().isEmpty() && imbpBean.getWithhold_day_quota().contains(",")) {
			inputString.append(",withhold_day_quota=" + imbpBean.getWithhold_day_quota().replace(",", "#"));
		}else if (!imbpBean.getWithhold_day_quota().isEmpty() && !imbpBean.getWithhold_day_quota().contains(",")){
			inputString.append(",withhold_day_quota=" + imbpBean.getWithhold_day_quota());
		}
		
		
		if (!imbpBean.getAdv_token().isEmpty()) {
			inputString.append(",adv_token=" + imbpBean.getAdv_token());
		}
		if (!imbpBean.getValidate_code().isEmpty()) {
			inputString.append(",validate_code=" + imbpBean.getValidate_code());
		}
		
		
		
		if (!imbpBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + imbpBean.getExtend_param());
		}

		System.out.println(inputString.toString());

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

		imbpBean.setEncodeString(sortString);
		if (encryptRealName != null) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"real_name")
					+ "&real_name="
					+ tool.textEncode(encryptRealName, "UTF-8"));
		}
		if (encryptCertNo != null) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"cert_no")
					+ "&cert_no="
					+ tool.textEncode(encryptCertNo, "UTF-8"));
		}
		if (encryptBankAccNo != null) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"bank_account_no")
					+ "&bank_account_no="
					+ tool.textEncode(encryptBankAccNo, "UTF-8"));
		}
		if (encryptAccName != null) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"account_name")
					+ "&account_name="
					+ tool.textEncode(encryptAccName, "UTF-8"));
		}
		if (encryptPhoneNo != null) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"phone_no")
					+ "&phone_no="
					+ tool.textEncode(encryptPhoneNo, "UTF-8"));
		}
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			imbpBean.setEncodeString(tool.removeFromString(
					imbpBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = imbpBean.getEncodeString() + "&sign="
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

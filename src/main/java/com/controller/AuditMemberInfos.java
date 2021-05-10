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
import com.mgs.bean.AuditMemberInfosBean;
import com.mgs.bean.MgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class AuditMemberInfos extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "AuditMemberInfos")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("amiBean") AuditMemberInfosBean amiBean,
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
		if (!amiBean.getAudit_order_no().isEmpty()) {
			inputString.append(",audit_order_no=" + amiBean.getAudit_order_no());
		}
		if (!amiBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + amiBean.getIdentity_id());
		}
		if (!amiBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + amiBean.getIdentity_type());
		}
		if (!amiBean.getHost_role().isEmpty()) {
			inputString.append(",host_role=" + amiBean.getHost_role());
		}
		if (!amiBean.getMember_type().isEmpty()) {
			inputString.append(",member_type=" + amiBean.getMember_type());
		}
		if (!amiBean.getCompany_name().isEmpty()) {
			inputString.append(",company_name=" + amiBean.getCompany_name());
		}
		if (!amiBean.getWebsite().isEmpty()) {
			inputString.append(",website=" + amiBean.getWebsite());
		}
		if (!amiBean.getAddress().isEmpty()) {
			inputString.append(",address=" + amiBean.getAddress());
		}
		if(!amiBean.getCert_effect_date().isEmpty()){
			inputString.append(",cert_effect_date=" + amiBean.getCert_effect_date());
		}
		if(!amiBean.getCert_invalid_date().isEmpty()){
			inputString.append(",cert_invalid_date=" + amiBean.getCert_invalid_date());
		}
		if (!amiBean.getClient_ip().isEmpty() && amiBean.getClient_ip().contains(",")) {
			inputString.append(",client_ip=" + amiBean.getClient_ip().replace(",", "#"));
		}else if (!amiBean.getClient_ip().isEmpty() && !amiBean.getClient_ip().contains(",")){
			inputString.append(",client_ip=" + amiBean.getClient_ip());
		}		
		String encryptLicenseNo = null;
		if (!amiBean.getLicense_no().isEmpty()) {
			try {
				encryptLicenseNo = rsa.encrypt(amiBean.getLicense_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",license_no=" + encryptLicenseNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!amiBean.getLicense_address().isEmpty()) {
			inputString.append(",license_address=" + amiBean.getLicense_address());
		}
		if (!amiBean.getLicense_expire_date().isEmpty()) {
			inputString.append(",license_expire_date=" + amiBean.getLicense_expire_date());
		}
		if (!amiBean.getBusiness_scope().isEmpty()) {
			inputString.append(",business_scope=" + amiBean.getBusiness_scope());
		}
		String encryptTelephone = null;
		if (!amiBean.getTelephone().isEmpty()) {
			try {
				encryptTelephone = rsa.encrypt(amiBean.getTelephone(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",telephone=" + encryptTelephone);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptEmail = null;
		if (!amiBean.getEmail().isEmpty()) {
			try {
				encryptEmail = rsa.encrypt(amiBean.getEmail(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",email=" + encryptEmail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptOrganizationNo = null;
		if (!amiBean.getOrganization_no().isEmpty()) {
			try {
				encryptOrganizationNo = rsa.encrypt(amiBean.getOrganization_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",organization_no=" + encryptOrganizationNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!amiBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + amiBean.getSummary());
		}
		String encryptLegalPerson = null;
		if (!amiBean.getLegal_person().isEmpty()) {
			try {
				encryptLegalPerson = rsa.encrypt(amiBean.getLegal_person(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",legal_person=" + encryptLegalPerson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String encryptCertNo = null;
		if (!amiBean.getCert_no().isEmpty()) {
			try {
				encryptCertNo = rsa.encrypt(amiBean.getCert_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",cert_no=" + encryptCertNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!amiBean.getCert_type().isEmpty()) {
			inputString.append(",cert_type=" + amiBean.getCert_type());
		}
		String encryptLegalPersonPhone = null;
		if (!amiBean.getLegal_person_phone().isEmpty()) {
			try {
				encryptLegalPersonPhone = rsa.encrypt(amiBean.getLegal_person_phone(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",legal_person_phone=" + encryptLegalPersonPhone);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!amiBean.getBank_code().isEmpty()) {
			inputString.append(",bank_code=" + amiBean.getBank_code());
		}
		String encryptBankAccNo = null;
		if (!amiBean.getBank_account_no().isEmpty()) {
			try {
				encryptBankAccNo = rsa.encrypt(amiBean.getBank_account_no(),
						mgsBaseBean.getEncryptkey());
				inputString.append(",bank_account_no=" + encryptBankAccNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!amiBean.getCard_type().isEmpty()) {
			inputString.append(",card_type=" + amiBean.getCard_type());
		}
		if (!amiBean.getCard_attribute().isEmpty()) {
			inputString.append(",card_attribute=" + amiBean.getCard_attribute());
		}
		if (!amiBean.getProvince().isEmpty()) {
			inputString.append(",province=" + amiBean.getProvince());
		}
		if (!amiBean.getCity().isEmpty()) {
			inputString.append(",city=" + amiBean.getCity());
		}
		if (!amiBean.getBank_branch().isEmpty()) {
			inputString.append(",bank_branch=" + amiBean.getBank_branch());
		}
		if (!amiBean.getFileName().isEmpty()) {
			inputString.append(",fileName=" + amiBean.getFileName());
		}
		if (!amiBean.getDigest().isEmpty()) {
			inputString.append(",digest=" + amiBean.getDigest());
		}
		if (!amiBean.getDigestType().isEmpty()) {
			inputString.append(",digestType=" + amiBean.getDigestType());
		}
		if (!amiBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + amiBean.getExtend_param());
		}

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

		amiBean.setEncodeString(sortString);
		if (!amiBean.getCompany_name().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"company_name")
					+ "&company_name="
					+ tool.textEncode(amiBean.getCompany_name(), "UTF-8"));
		}
		if (!amiBean.getWebsite().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"website")
					+ "&website="
					+ tool.textEncode(amiBean.getWebsite(), "UTF-8"));
		}
		if (!amiBean.getAddress().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"address")
					+ "&address="
					+ tool.textEncode(amiBean.getAddress(), "UTF-8"));
		}
		if (encryptLicenseNo != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"license_no")
					+ "&license_no="
					+ tool.textEncode(encryptLicenseNo, "UTF-8"));
		}
		if (!amiBean.getLicense_address().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"license_address")
					+ "&license_address="
					+ tool.textEncode(amiBean.getLicense_address(), "UTF-8"));
		}
		if (!amiBean.getBusiness_scope().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"business_scope")
					+ "&business_scope="
					+ tool.textEncode(amiBean.getBusiness_scope(), "UTF-8"));
		}
		if (encryptTelephone != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"telephone")
					+ "&telephone="
					+ tool.textEncode(encryptTelephone, "UTF-8"));
		}
		if (encryptEmail != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"email")
					+ "&email="
					+ tool.textEncode(encryptEmail, "UTF-8"));
		}
		if (encryptOrganizationNo != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"organization_no")
					+ "&organization_no="
					+ tool.textEncode(encryptOrganizationNo, "UTF-8"));
		}
		if (!amiBean.getSummary().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(amiBean.getSummary(), "UTF-8"));
		}
		if (encryptLegalPerson != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"legal_person")
					+ "&legal_person="
					+ tool.textEncode(encryptLegalPerson, "UTF-8"));
		}
		if (encryptCertNo != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"cert_no")
					+ "&cert_no="
					+ tool.textEncode(encryptCertNo, "UTF-8"));
		}
		if (encryptLegalPersonPhone != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"legal_person_phone")
					+ "&legal_person_phone="
					+ tool.textEncode(encryptLegalPersonPhone, "UTF-8"));
		}
		if (encryptBankAccNo != null) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"bank_account_no")
					+ "&bank_account_no="
					+ tool.textEncode(encryptBankAccNo, "UTF-8"));
		}
		if (!amiBean.getProvince().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"province")
					+ "&province="
					+ tool.textEncode(amiBean.getProvince(), "UTF-8"));
		}
		if (!amiBean.getCity().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"city")
					+ "&city="
					+ tool.textEncode(amiBean.getCity(), "UTF-8"));
		}
		if (!amiBean.getBank_branch().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"bank_branch")
					+ "&bank_branch="
					+ tool.textEncode(amiBean.getBank_branch(), "UTF-8"));
		}
		if (!amiBean.getFileName().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"fileName")
					+ "&fileName="
					+ tool.textEncode(amiBean.getFileName(), "UTF-8"));
		}
		if (!amiBean.getDigest().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"digest=")
					+ "&digest="
					+ tool.textEncode(amiBean.getDigest(), "UTF-8"));
		}
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getMemo().isEmpty()) {
			amiBean.setEncodeString(tool.removeFromString(
					amiBean.getEncodeString(), "&", "&", "startswith",
					"memo")
					+ "&memo="
					+ tool.textEncode(mgsBaseBean.getMemo(), "UTF-8"));
		}
		

		String requestString = amiBean.getEncodeString() + "&sign="
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

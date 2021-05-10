package com.controller.sh_bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh_bank.bean.SetTradePassword_shBean;
import com.sh_bank.bean.ShMasBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/**
 *
 * @author linshunxiang
 *
 */
@Controller
public class SetTradePassword_sh extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@RequestMapping(value = "SetTradePassword_sh")
	public void doPost(@ModelAttribute("mgsBaseBean") ShMasBaseBean mgsBaseBean,
			@ModelAttribute("imbpBean") SetTradePassword_shBean imbpBean, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
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
			inputString.append(",request_time=" + mgsBaseBean.getRequest_time());
		}
		if (!mgsBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",cashdesk_addr_category=" + mgsBaseBean.getCashdesk_addr_category());
		}
		if (!mgsBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + mgsBaseBean.getPartner_id());
		}
		if (!mgsBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset=" + mgsBaseBean.get_input_charset());
		}
		if (!mgsBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + mgsBaseBean.getSign_type());
		}
		if (!mgsBaseBean.getSign_version().isEmpty()) {
			inputString.append(",sign_version=" + mgsBaseBean.getSign_version());
		}
		if (!mgsBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version=" + mgsBaseBean.getEncrypt_version());
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
		if (!imbpBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + imbpBean.getIdentity_id());
		}
		if (!imbpBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + imbpBean.getIdentity_type());
		}
		if (!imbpBean.getWithhold_param().isEmpty() && imbpBean.getWithhold_param().contains(",")) {
			inputString.append(",withhold_param=" + imbpBean.getWithhold_param().replace(",", "#"));
		} else if (!imbpBean.getWithhold_param().isEmpty() && !imbpBean.getWithhold_param().contains(",")) {
			inputString.append(",withhold_param=" + imbpBean.getWithhold_param());
		}

		if (!imbpBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + imbpBean.getExtend_param());
		}

		System.out.println(inputString.toString());

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replace("#", ",");

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
		if (!imbpBean.getWithhold_param().isEmpty()) {
			imbpBean.setEncodeString(
					tool.removeFromString(imbpBean.getEncodeString(), "&", "&", "startswith", "withhold_param")
							+ "&withhold_param=" + tool.textEncode(imbpBean.getWithhold_param(), "UTF-8"));
		}

		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			imbpBean.setEncodeString(
					tool.removeFromString(imbpBean.getEncodeString(), "&", "&", "startswith", "notify_url")
							+ "&notify_url=" + tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			imbpBean.setEncodeString(
					tool.removeFromString(imbpBean.getEncodeString(), "&", "&", "startswith", "return_url")
							+ "&return_url=" + tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = imbpBean.getEncodeString() + "&sign=" + tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(tool.post(mgsBaseBean.getUrl(), requestString), "UTF-8");
		System.out.println(responseString);

		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}

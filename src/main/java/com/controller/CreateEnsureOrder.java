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
import com.b2c.bean.CreateEnsureOrderBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateEnsureOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateEnsureOrder")
	public void doPost(@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
			@ModelAttribute("ceoBean") CreateEnsureOrderBean ceoBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		if (!ceoBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + ceoBean.getOut_trade_no());
		}
		if (!ceoBean.getBuyer_identity_id().isEmpty()) {
			inputString.append(",buyer_identity_id="
					+ ceoBean.getBuyer_identity_id());
		}
		if (!ceoBean.getBuyer_identity_type().isEmpty()) {
			inputString.append(",buyer_identity_type=" + ceoBean.getBuyer_identity_type());
		}
		if (!ceoBean.getSeller_identity_id().isEmpty()) {
			inputString.append(",seller_identity_id="
					+ ceoBean.getSeller_identity_id());
		}
		if (!ceoBean.getSeller_identity_type().isEmpty()) {
			inputString.append(",seller_identity_type=" + ceoBean.getSeller_identity_type());
		}
		if (!ceoBean.getSeller_account_type().isEmpty()) {
			inputString.append(",seller_account_type=" + ceoBean.getSeller_account_type());
		}
		if (!ceoBean.getAmount().isEmpty()) {
			inputString.append(",amount="
					+ ceoBean.getAmount());
		}
		if (!ceoBean.getProduct_desc().isEmpty()) {
			inputString.append(",product_desc=" + ceoBean.getProduct_desc());
		}
		if (!ceoBean.getCan_repeat().isEmpty()) {
			inputString.append(",can_repeat=" + ceoBean.getCan_repeat());
		}
		if (!ceoBean.getExpired_time().isEmpty()) {
			inputString.append(",expired_time=" + ceoBean.getExpired_time());
		}
		if (!ceoBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + ceoBean.getExtend_param());
		}
		if (!ceoBean.getPayer_ip().isEmpty()) {
			inputString.append(",payer_ip=" + ceoBean.getPayer_ip());
		}
		if (!ceoBean.getDevice_id().isEmpty()) {
			inputString.append(",device_id=" + ceoBean.getDevice_id());
		}

		String quickPayString = null;
		if (!ceoBean.getPay_method().isEmpty()
				&& ceoBean.getPay_method().startsWith("quick_pay")) {
			Tools tool = new Tools();
			quickPayString = tool.quickPayEncrypt(ceoBean.getPay_method(),
					b2cBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		} else if (!ceoBean.getPay_method().isEmpty()
				&& !ceoBean.getPay_method().startsWith("quick_pay")) {
			quickPayString = ceoBean.getPay_method().replaceAll(",",
					"replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		}

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replaceAll("replaceFlag", ",");
		System.out.println(signString);

		String sign = null;
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
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

		ceoBean.setEncodeString(sortString);
		if (!b2cBaseBean.getNotify_url().isEmpty()) {
			ceoBean.setEncodeString(tool.removeFromString(
					ceoBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!b2cBaseBean.getReturn_url().isEmpty()) {
			ceoBean.setEncodeString(tool.removeFromString(
					ceoBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!ceoBean.getExtend_param().isEmpty()) {
			ceoBean.setEncodeString(tool.removeFromString(
					ceoBean.getEncodeString(), "&", "&", "startswith",
					"extend_param")
					+ "&extend_param="
					+ tool.textEncode(ceoBean.getExtend_param(), "UTF-8"));
		}
		if (!ceoBean.getPay_method().isEmpty()) {
			ceoBean.setEncodeString(tool.removeFromString(
					ceoBean.getEncodeString(), "&", "&", "startswith",
					"pay_method")
					+ "&pay_method="
					+ tool.textEncode(quickPayString, "UTF-8"));
		}

		String requestString = ceoBean.getEncodeString().replaceAll(
				"replaceFlag", ",")
				+ "&sign=" + tool.textEncode(sign, "UTF-8");
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

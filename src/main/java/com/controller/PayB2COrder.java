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
import com.b2c.bean.PayB2COrderBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class PayB2COrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "PayB2COrder")
	public void doPost(@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
			@ModelAttribute("poBean") PayB2COrderBean poBean,
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
		
		if (!poBean.getOut_pay_no().isEmpty()) {
			inputString.append(",out_pay_no=" + poBean.getOut_pay_no());
		}
		if (!poBean.getOut_trade_no_list().isEmpty()) {
			inputString.append(",out_trade_no_list=" + poBean.getOut_trade_no_list());
		}
		if (!poBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + poBean.getExtend_param());
		}
		if (!poBean.getPayer_ip().isEmpty()) {
			inputString.append(",payer_ip=" + poBean.getPayer_ip());
		}

		String quickPayString = null;
		if (!poBean.getPay_method().isEmpty()
				&& poBean.getPay_method().startsWith("quick_pay")) {
			Tools tool = new Tools();
			quickPayString = tool.quickPayEncrypt(poBean.getPay_method(),
					b2cBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		} else if (!poBean.getPay_method().isEmpty()
				&& !poBean.getPay_method().startsWith("quick_pay")) {
			quickPayString = poBean.getPay_method().replaceAll(",",
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

		poBean.setEncodeString(sortString);
		if (!b2cBaseBean.getNotify_url().isEmpty()) {
			poBean.setEncodeString(tool.removeFromString(
					poBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!b2cBaseBean.getReturn_url().isEmpty()) {
			poBean.setEncodeString(tool.removeFromString(
					poBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!poBean.getExtend_param().isEmpty()) {
			poBean.setEncodeString(tool.removeFromString(
					poBean.getEncodeString(), "&", "&", "startswith",
					"extend_param")
					+ "&extend_param="
					+ tool.textEncode(poBean.getExtend_param(), "UTF-8"));
		}
		if (!poBean.getPay_method().isEmpty()) {
			poBean.setEncodeString(tool.removeFromString(
					poBean.getEncodeString(), "&", "&", "startswith",
					"pay_method")
					+ "&pay_method="
					+ tool.textEncode(quickPayString, "UTF-8"));
		}

		String requestString = poBean.getEncodeString().replaceAll(
				"replaceFlag", ",")
				+ "&sign=" + tool.textEncode(sign, "UTF-8");
		System.out.println("????????????: " + requestString);

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

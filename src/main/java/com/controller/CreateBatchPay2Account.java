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
import com.b2c.bean.CreateBatchPay2AccountBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/**
 * 
 * @author sunjie
 * 2018-9-5 增加字段ACCOUNT_TYPE vianna_zhou
 */
@Controller
public class CreateBatchPay2Account extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateBatchPay2Account")
	public void doPost(
			@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
			@ModelAttribute("cbp2aBean") CreateBatchPay2AccountBean cbp2aBean,
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
		if (!cbp2aBean.getBatch_no().isEmpty()) {
			inputString.append(",batch_no=" + cbp2aBean.getBatch_no());
		}
		if (!cbp2aBean.getDetail_list().isEmpty()) {
			inputString.append(",detail_list=" + cbp2aBean.getDetail_list());
		}
		if (!cbp2aBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + cbp2aBean.getExtend_param());
		}

		/* 2018-09-05 ADD */
		if (!cbp2aBean.getAccount_type().isEmpty()) {
			inputString.append(",account_type=" + cbp2aBean.getAccount_type());
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

		cbp2aBean.setEncodeString(sortString);
		if (!b2cBaseBean.getNotify_url().isEmpty()) {
			cbp2aBean.setEncodeString(tool.removeFromString(
					cbp2aBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!b2cBaseBean.getReturn_url().isEmpty()) {
			cbp2aBean.setEncodeString(tool.removeFromString(
					cbp2aBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!cbp2aBean.getExtend_param().isEmpty()) {
			cbp2aBean.setEncodeString(tool.removeFromString(
					cbp2aBean.getEncodeString(), "&", "&", "startswith",
					"extend_param")
					+ "&extend_param="
					+ tool.textEncode(cbp2aBean.getExtend_param(), "UTF-8"));
		}

		String requestString = cbp2aBean.getEncodeString().replaceAll(
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

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
import com.mgs.bean.MgsBaseBean;
import com.mgs.bean.QueryAccountDetailsBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class QueryAccountDetails extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "QueryAccountDetails")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("qadBean") QueryAccountDetailsBean qadBean,
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
		if (!qadBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + qadBean.getIdentity_id());
		}
		if (!qadBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + qadBean.getIdentity_type());
		}
		if (!qadBean.getAccount_type().isEmpty()) {
			inputString.append(",account_type=" + qadBean.getAccount_type());
		}
		if (!qadBean.getStart_time().isEmpty()) {
			inputString.append(",start_time=" + qadBean.getStart_time());
		}
		if (!qadBean.getEnd_time().isEmpty()) {
			inputString.append(",end_time=" + qadBean.getEnd_time());
		}
		if (!qadBean.getPage_no().isEmpty()) {
			inputString.append(",page_no=" + qadBean.getPage_no());
		}
		if (!qadBean.getPage_size().isEmpty()) {
			inputString.append(",page_size=" + qadBean.getPage_size());
		}
		if (!qadBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + qadBean.getExtend_param());
		}
		if (!qadBean.getAccount_identity().isEmpty()) {
			inputString.append(",account_identity=" + qadBean.getAccount_identity());
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

		qadBean.setEncodeString(sortString);
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			qadBean.setEncodeString(tool.removeFromString(
					qadBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			qadBean.setEncodeString(tool.removeFromString(
					qadBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = qadBean.getEncodeString() + "&sign="
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

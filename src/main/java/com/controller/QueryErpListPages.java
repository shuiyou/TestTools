package com.controller;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.pas.bean.PasBaseBean;
import com.pas.bean.QueryErpListPagesBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hanxiaodi on 18/6/12.
 */
@Controller
public class QueryErpListPages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "QueryErpListPages")
	public void doPost(@ModelAttribute("pasBaseBean") PasBaseBean pasBaseBean,
			@ModelAttribute("qelpBean") QueryErpListPagesBean qelpBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
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

		if (!pasBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + pasBaseBean.getMemo());
		}
		if (!qelpBean.getOut_request_no().isEmpty()) {
			inputString
					.append(",out_request_no=" + qelpBean.getOut_request_no());
		}

		if (!qelpBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + qelpBean.getExtend_param());
		}
		if (!qelpBean.getErp_type().isEmpty()) {
			inputString.append(",erp_type=" + qelpBean.getErp_type());
		}

		String encryptErpParam = null;
		if (!qelpBean.getErp_param().isEmpty()) {
			try {
				encryptErpParam = rsa.encrypt(qelpBean.getErp_param(),
						pasBaseBean.getEncryptkey());
				inputString.append(",erp_param=" + encryptErpParam);
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

		qelpBean.setEncodeString(sortString);


		if (encryptErpParam != null) {
			qelpBean.setEncodeString(tool.removeFromString(
					qelpBean.getEncodeString(), "&", "&", "startswith",
					"erp_param")
					+ "&erp_param="
					+ tool.textEncode(encryptErpParam, "UTF-8"));
		}


		if (!pasBaseBean.getNotify_url().isEmpty()) {
			qelpBean.setEncodeString(tool.removeFromString(
					qelpBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(pasBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			qelpBean.setEncodeString(tool.removeFromString(
					qelpBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(pasBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = qelpBean.getEncodeString() + "&sign="
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

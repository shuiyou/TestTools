package com.controller.egs;

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
import com.egs.bean.EgsBaseBean;
import com.egs.bean.EgsMemberRevokeBean;


@Controller
public class EgsMemberRevoke extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "EgsMemberRevoke")
	public void doPost(@ModelAttribute("egsBaseBean") EgsBaseBean egsBaseBean,
			@ModelAttribute("camBean") EgsMemberRevokeBean camBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		egsBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!egsBaseBean.getService().isEmpty()) {
			inputString.append("service=" + egsBaseBean.getService());
		}
		if (!egsBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + egsBaseBean.getVersion());
		}
		if (!egsBaseBean.getRequest_time().isEmpty()) {
			inputString
					.append(",request_time=" + egsBaseBean.getRequest_time());
		}
		if (!egsBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + egsBaseBean.getPartner_id());
		}
		if (!egsBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ egsBaseBean.get_input_charset());
		}
		if (!egsBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + egsBaseBean.getSign_type());
		}
		if (!egsBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + egsBaseBean.getSign_version());
		}
		if (!egsBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ egsBaseBean.getEncrypt_version());
		}
		if (!egsBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + egsBaseBean.getNotify_url());
		}
		if (!egsBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + egsBaseBean.getReturn_url());
		}
		if (!egsBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + egsBaseBean.getMemo());
			
		}
		if (!egsBaseBean.getClient_ip().isEmpty() && egsBaseBean.getClient_ip().contains(",")) {
			inputString.append(",client_ip=" + egsBaseBean.getClient_ip().replace(",", "#"));
		}else if (!egsBaseBean.getClient_ip().isEmpty() && !egsBaseBean.getClient_ip().contains(",")){
			inputString.append(",client_ip=" + egsBaseBean.getClient_ip());
		}
		
		
		if (!camBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + camBean.getIdentity_id());
		}
		if (!camBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + camBean.getIdentity_type());
		}
		if (!camBean.getMember_type().isEmpty()) {
			inputString.append(",member_type=" + camBean.getMember_type());
		}

		if (!camBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + camBean.getExtend_param());
		}

		System.out.println(inputString.toString());

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replace("#", ",");

		String sign = null;
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		if (egsBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(egsBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (egsBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + egsBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		camBean.setEncodeString(sortString);
		if (!egsBaseBean.getNotify_url().isEmpty()) {
			camBean.setEncodeString(tool.removeFromString(
					camBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(egsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!egsBaseBean.getReturn_url().isEmpty()) {
			camBean.setEncodeString(tool.removeFromString(
					camBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(egsBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = camBean.getEncodeString() + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(egsBaseBean.getUrl(), requestString), "UTF-8");
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

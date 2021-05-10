package com.controller;

import java.io.IOException;
import java.util.HashMap;

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
import com.pas.bean.PasBaseBean;
import com.pas.bean.QueryBranchListPagesBean;

/**
 * 
 * @author yangchenye
 *
 */
@Controller
public class QueryBranchListPages extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "QueryBranchListPages")
	public void doPost(@ModelAttribute("pasBaseBean") PasBaseBean pasBaseBean,
			@ModelAttribute("qblpBean") QueryBranchListPagesBean qblpBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RSAUtil rsa = new RSAUtil();
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
		if (!pasBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + pasBaseBean.getNotify_url());
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + pasBaseBean.getReturn_url());
		}
		if (!pasBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + pasBaseBean.getMemo());
		}
		
		if (!qblpBean.getOut_request_no().isEmpty()) {
			inputString.append(",out_request_no=" + qblpBean.getOut_request_no());
		}
		if (!qblpBean.getBank_code().isEmpty()) {
			inputString.append(",bank_code=" + qblpBean.getBank_code());
		}
		if (!qblpBean.getProv_id().isEmpty()) {
			inputString.append(",prov_id=" + qblpBean.getProv_id());
		}
		if (!qblpBean.getAlike_prov_alias().isEmpty()) {
			inputString.append(",alike_prov_alias=" + qblpBean.getAlike_prov_alias());
		}
		if (!qblpBean.getCity_id().isEmpty()) {
			inputString.append(",city_id=" + qblpBean.getCity_id());
		}
		if (!qblpBean.getAlike_city_alias().isEmpty()) {
			inputString.append(",alike_city_alias=" + qblpBean.getAlike_city_alias());
		}
		if (!qblpBean.getAlike_area_alias().isEmpty()) {
			inputString.append(",alike_area_alias=" + qblpBean.getAlike_area_alias());
		}
		if (!qblpBean.getAlike_branch_alias().isEmpty()) {
			inputString.append(",alike_branch_alias=" + qblpBean.getAlike_branch_alias());
		}

		if (!qblpBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + qblpBean.getExtend_param().replace(",","replaceFlag"));
		}


		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		sortString =sortString.replace("replaceFlag", ",");
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


		qblpBean.setEncodeString(sortString);



		if (!pasBaseBean.getNotify_url().isEmpty()) {
			qblpBean.setEncodeString(tool.removeFromString(
					qblpBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(pasBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!pasBaseBean.getReturn_url().isEmpty()) {
			qblpBean.setEncodeString(tool.removeFromString(
					qblpBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(pasBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = qblpBean.getEncodeString() + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(pasBaseBean.getUrl(), requestString), "UTF-8");
		System.out.println(responseString);

		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		this.doPost(request, response);
	}

}

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
import com.mas.bean.CreateP2PHostingBorrowingTargetBean;
import com.mas.bean.MasBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateP2PHostingBorrowingTarget extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateP2PHostingBorrowingTarget")
	public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("chwBean") CreateP2PHostingBorrowingTargetBean chwBean, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		masBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!masBaseBean.getService().isEmpty()) {
			inputString.append("service=" + masBaseBean.getService());
		}
		if (!masBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + masBaseBean.getVersion());
		}
		if (!masBaseBean.getRequest_time().isEmpty()) {
			inputString.append(",request_time=" + masBaseBean.getRequest_time());
		}
		if (!masBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + masBaseBean.getPartner_id());
		}
		if (!masBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset=" + masBaseBean.get_input_charset());
		}
		if (!masBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + masBaseBean.getSign_type());
		}
		if (!masBaseBean.getSign_version().isEmpty()) {
			inputString.append(",sign_version=" + masBaseBean.getSign_version());
		}
		if (!masBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version=" + masBaseBean.getEncrypt_version());
		}
		if (!masBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + masBaseBean.getNotify_url());
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + masBaseBean.getReturn_url());
		}
		if (!masBaseBean.getMemo().isEmpty()) {
			inputString.append(",memo=" + masBaseBean.getMemo());
		}
		if (!chwBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_id=" + chwBean.getGoods_id());
		}
		if (!chwBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + chwBean.getSummary());
		}
		if (!chwBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_name=" + chwBean.getGoods_id());
		}
		if (!chwBean.getAnnual_yield().isEmpty()) {
			inputString.append(",annual_yield=" + chwBean.getAnnual_yield());
		}
		if (!chwBean.getTerm().isEmpty()) {
			inputString.append(",term=" + chwBean.getTerm());
		}
		if (!chwBean.getRepay_method().isEmpty()) {
			inputString.append(",repay_method=" + chwBean.getRepay_method());
		}
		if (!chwBean.getGuarantee_method().isEmpty()) {
			inputString.append(",guarantee_method=" + chwBean.getGuarantee_method());
		}
		if (!chwBean.getDebtor_list().isEmpty()) {
			inputString.append(",debtor_list=" + chwBean.getDebtor_list());
		}
		if (!chwBean.getInvestor_list().isEmpty()) {
			inputString.append(",investor_list=" + chwBean.getInvestor_list());
		}
		if (!chwBean.getTotal_amount().isEmpty()) {
			inputString.append(",total_amount=" + chwBean.getTotal_amount());
		}
		if (!chwBean.getBegin_date().isEmpty()) {
			inputString.append(",begin_date=" + chwBean.getBegin_date());
		}
		if (!chwBean.getUrl2().isEmpty()) {
			inputString.append(",url=" + chwBean.getUrl2());
		}
		if (!chwBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + chwBean.getExtend_param());
		}

		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String signString = tool.removeFromString(sortString, "&", "&", "startswith", "sign").replaceAll("replaceFlag",
				",");
		System.out.println(signString);

		String sign = null;
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		if (masBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(masBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (masBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + masBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		chwBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			chwBean.setEncodeString(
					tool.removeFromString(chwBean.getEncodeString(), "&", "&", "startswith", "notify_url")
							+ "&notify_url=" + tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			chwBean.setEncodeString(
					tool.removeFromString(chwBean.getEncodeString(), "&", "&", "startswith", "return_url")
							+ "&return_url=" + tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}

		String requestString = chwBean.getEncodeString().replaceAll("replaceFlag", ",") + "&sign="
				+ tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(tool.post(masBaseBean.getUrl(), requestString), "UTF-8");
		System.out.println(responseString);

		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

}

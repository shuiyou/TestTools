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
import com.mas.bean.CreateSingleHostingPayTradeBean;
import com.mas.bean.MasBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateSingleHostingPayTrade extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateSingleHostingPayTrade")
	public void doPost(
			@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("cshptBean") CreateSingleHostingPayTradeBean cshptBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
			inputString
					.append(",request_time=" + masBaseBean.getRequest_time());
		}
		if (!masBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + masBaseBean.getPartner_id());
		}
		if (!masBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ masBaseBean.get_input_charset());
		}
		if (!masBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + masBaseBean.getSign_type());
		}
		if (!masBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + masBaseBean.getSign_version());
		}
		if (!masBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ masBaseBean.getEncrypt_version());
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
		if (!cshptBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + cshptBean.getOut_trade_no());
		}
		if (!cshptBean.getOut_trade_code().isEmpty()) {
			inputString.append(",out_trade_code="
					+ cshptBean.getOut_trade_code());
		}
		if (!cshptBean.getPayee_partner_id().isEmpty()) {
			inputString.append(",payee_partner_id="
					+ cshptBean.getPayee_partner_id());
		}
		if (!cshptBean.getPayer_partner_id().isEmpty()) {
			inputString.append(",payer_partner_id="
					+ cshptBean.getPayer_partner_id());
		}
		if (!cshptBean.getTrade_related_no().isEmpty()) {
			inputString.append(",trade_related_no="
					+ cshptBean.getTrade_related_no());
		}
		if (!cshptBean.getPayee_identity_id().isEmpty()) {
			inputString.append(",payee_identity_id="
					+ cshptBean.getPayee_identity_id());
		}
		if (!cshptBean.getPayee_identity_type().isEmpty()) {
			inputString.append(",payee_identity_type="
					+ cshptBean.getPayee_identity_type());
		}
		if (!cshptBean.getAccount_type().isEmpty()) {
			inputString.append(",account_type=" + cshptBean.getAccount_type());
		}
		if (!cshptBean.getAmount().isEmpty()) {
			inputString.append(",amount=" + cshptBean.getAmount());
		}
		if (!cshptBean.getUser_fee().isEmpty()) {
			inputString.append(",user_fee=" + cshptBean.getUser_fee());
		}
		if (!cshptBean.getUser_ip().isEmpty()) {
			inputString.append(",user_ip=" + cshptBean.getUser_ip());
		}
		if (!cshptBean.getSplit_list().isEmpty()) {
			inputString.append(",split_list=" + cshptBean.getSplit_list());
		}
		if (!cshptBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + cshptBean.getSummary());
		}
		if (!cshptBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + cshptBean.getExtend_param());
		}
		if (!cshptBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_id=" + cshptBean.getGoods_id());
		}
		if (!cshptBean.getOnce_withdraw().isEmpty()) {
			inputString.append(",once_withdraw=" + cshptBean.getOnce_withdraw());
		}		if (!cshptBean.getCard_id().isEmpty()) {
			inputString.append(",card_id=" + cshptBean.getCard_id());
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

		cshptBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			cshptBean.setEncodeString(tool.removeFromString(
					cshptBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			cshptBean.setEncodeString(tool.removeFromString(
					cshptBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!cshptBean.getSummary().isEmpty()) {
			cshptBean.setEncodeString(tool.removeFromString(
					cshptBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(cshptBean.getSummary(), "UTF-8"));
		}

		String requestString = cshptBean.getEncodeString().replaceAll(
				"replaceFlag", ",")
				+ "&sign=" + tool.textEncode(sign, "UTF-8");
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(masBaseBean.getUrl(), requestString), "UTF-8");
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

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
import com.mgs.bean.BalanceFreezeBean;
import com.mgs.bean.MgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class BalanceFreeze extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String encodeString = null;

	@RequestMapping(value = "BalanceFreeze")
	public void doPost(@ModelAttribute("mgsBaseBean") MgsBaseBean mgsBaseBean,
			@ModelAttribute("bfBean") BalanceFreezeBean bfBean,
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
		if (!bfBean.getOut_freeze_no().isEmpty()) {
			inputString.append(",out_freeze_no=" + bfBean.getOut_freeze_no());
		}
		if (!bfBean.getIdentity_id().isEmpty()) {
			inputString.append(",identity_id=" + bfBean.getIdentity_id());
		}
		if (!bfBean.getIdentity_type().isEmpty()) {
			inputString.append(",identity_type=" + bfBean.getIdentity_type());
		}
		if (!bfBean.getAccount_type().isEmpty()) {
			inputString.append(",account_type=" + bfBean.getAccount_type());
		}
		if (!bfBean.getAmount().isEmpty()) {
			inputString.append(",amount=" + bfBean.getAmount());
		}
		if (!bfBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_id=" + bfBean.getGoods_id());
		}
		if (!bfBean.getOut_trade_code().isEmpty()) {
			inputString.append(",out_trade_code=" + bfBean.getOut_trade_code());
		}
		if (!bfBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + bfBean.getSummary());
		}
		if (!bfBean.getClient_ip().isEmpty() && bfBean.getClient_ip().contains(",")) {
			inputString.append(",client_ip=" + bfBean.getClient_ip().replace(",", "#"));
		}else if (!bfBean.getClient_ip().isEmpty() && !bfBean.getClient_ip().contains(",")){
			inputString.append(",client_ip=" + bfBean.getClient_ip());
		}
		if (!bfBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + bfBean.getExtend_param());
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

		bfBean.setEncodeString(sortString);
		if (!mgsBaseBean.getNotify_url().isEmpty()) {
			bfBean.setEncodeString(tool.removeFromString(
					bfBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(mgsBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!mgsBaseBean.getReturn_url().isEmpty()) {
			bfBean.setEncodeString(tool.removeFromString(
					bfBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(mgsBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!bfBean.getSummary().isEmpty()) {
			bfBean.setEncodeString(tool.removeFromString(
					bfBean.getEncodeString(), "&", "&", "startswith", "summary")
					+ "&summary="
					+ tool.textEncode(bfBean.getSummary(), "UTF-8"));
		}

		String replaceString = null;
		encodeString = tool.removeFromString(bfBean.getEncodeString(), "&",
				"&", "startswith", "summary");
		replaceString = encodeString + "&summary="
				+ tool.textEncode(bfBean.getSummary(), "UTF-8");

		String requestString = replaceString + "&sign="
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

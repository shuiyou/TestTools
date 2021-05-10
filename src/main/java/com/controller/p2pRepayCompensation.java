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
import com.mas.bean.MasBaseBean;
import com.mas.bean.P2PRepayCompensationBean;
import com.mas.bean.PayHostingTradeBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class p2pRepayCompensation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "p2pRepayCompensation")
	public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("phtBean") P2PRepayCompensationBean phtBean,
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
		if (!masBaseBean.getCashdesk_addr_category().isEmpty()) {
			inputString.append(",cashdesk_addr_category=" + masBaseBean.getCashdesk_addr_category());
		}
		if (!phtBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + phtBean.getOut_trade_no());
		}
		if (!phtBean.getOrig_out_trade_no().isEmpty()) {
			inputString.append(",orig_out_trade_no="
					+ phtBean.getOrig_out_trade_no());
		}
		if (!phtBean.getTrade_close_time().isEmpty()) {
			inputString.append(",trade_close_time="
					+ phtBean.getTrade_close_time());
		}
		
		if (!phtBean.getPayer_identity_id().isEmpty()) {
			inputString.append(",payer_identity_id=" + phtBean.getPayer_identity_id());
		}
		if (!phtBean.getPayer_identity_type().isEmpty()) {
			inputString.append(",payer_identity_type=" + phtBean.getPayer_identity_type());
		}
		if (!phtBean.getPayer_fee().isEmpty()) {
			inputString.append(",payer_fee=" + phtBean.getPayer_fee());
		}
		if (!phtBean.getCan_repay_on_failed().isEmpty()) {
			inputString.append(",can_repay_on_failed=" + phtBean.getCan_repay_on_failed());
		}
		if (!phtBean.getPayer_account_identity().isEmpty()) {
			inputString.append(",payer_account_identity=" + phtBean.getPayer_account_identity());
		}
		
		if (!phtBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + phtBean.getExtend_param());
		}


		if (!phtBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + phtBean.getSummary());
		}
		if (!phtBean.getPayer_ip().isEmpty()) {
			inputString.append(",payer_ip=" + phtBean.getPayer_ip());
		}
		String quickPayString = null;
		if (!phtBean.getPay_method().isEmpty()
				&& phtBean.getPay_method().startsWith("quick_pay")) {
			Tools tool = new Tools();
			quickPayString = tool.quickPayEncrypt(phtBean.getPay_method(),
					masBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		} else {
			quickPayString = phtBean.getPay_method().replaceAll(",",
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

		phtBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			phtBean.setEncodeString(tool.removeFromString(
					phtBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			phtBean.setEncodeString(tool.removeFromString(
					phtBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!phtBean.getPay_method().isEmpty()) {
			phtBean.setEncodeString(tool.removeFromString(
					phtBean.getEncodeString(), "&", "&", "startswith",
					"pay_method")
					+ "&pay_method="
					+ tool.textEncode(quickPayString, "UTF-8"));
		}

		String requestString = phtBean.getEncodeString().replaceAll(
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

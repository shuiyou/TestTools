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
import com.mas.bean.FundCreatePurchaseTradeBean;
import com.mas.bean.MasBaseBean;
@Controller
public class FundCreatePurchaseTrade  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "FundCreatePurchaseTrade")
	public void doPost(
			@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("fcptBean") FundCreatePurchaseTradeBean fcptBean,
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
			inputString.append(",cashdesk_addr_category="
					+ masBaseBean.getCashdesk_addr_category());
		}
		if (!fcptBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + fcptBean.getOut_trade_no());
		}
		if (!fcptBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + fcptBean.getSummary());
		}
		if (!fcptBean.getTrade_close_time().isEmpty()) {
			inputString.append(",trade_close_time="
					+ fcptBean.getTrade_close_time());
		}
		if (!fcptBean.getCan_repay_on_failed().isEmpty()) {
			inputString.append(",can_repay_on_failed="
					+ fcptBean.getCan_repay_on_failed());
		}
		if (!fcptBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + fcptBean.getExtend_param());
		}
		if (!fcptBean.getPayer_id().isEmpty()) {
			inputString.append(",payer_id="
					+ fcptBean.getPayer_id());
		}
		if (!fcptBean.getPayer_identity_type().isEmpty()) {
			inputString.append(",payer_identity_type="
					+ fcptBean.getPayer_identity_type());
		}
		if (!fcptBean.getPayer_ip().isEmpty()) {
			inputString.append(",payer_ip=" + fcptBean.getPayer_ip());
		}
		if (!fcptBean.getFund_inst_code().isEmpty()) {
			inputString.append(",fund_inst_code=" + fcptBean.getFund_inst_code());
		}
		if (!fcptBean.getFund_prod_code().isEmpty()) {
			inputString.append(",fund_prod_code=" + fcptBean.getFund_prod_code());
		}
		if (!fcptBean.getFund_prod_type().isEmpty()) {
			inputString.append(",fund_prod_type=" + fcptBean.getFund_prod_type());
		}
		if (!fcptBean.getFund_trade_type().isEmpty()) {
			inputString.append(",fund_trade_type=" + fcptBean.getFund_trade_type());
		}
		if (!fcptBean.getPurchase_order_no().isEmpty()) {
			inputString.append(",purchase_order_no=" + fcptBean.getPurchase_order_no());
		}
		
		
		
		
		String quickPayString = null;
		if (!fcptBean.getPay_method().isEmpty()
				&& fcptBean.getPay_method().startsWith("quick_pay")) {
			Tools tool = new Tools();
			quickPayString = tool.quickPayEncrypt(fcptBean.getPay_method(),
					masBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		} else {
			quickPayString = fcptBean.getPay_method().replaceAll(",",
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

		fcptBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			fcptBean.setEncodeString(tool.removeFromString(
					fcptBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			fcptBean.setEncodeString(tool.removeFromString(
					fcptBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!masBaseBean.getMemo().isEmpty()) {
			fcptBean.setEncodeString(tool.removeFromString(
					fcptBean.getEncodeString(), "&", "&", "startswith",
					"memo")
					+ "&memo="
					+ tool.textEncode(masBaseBean.getMemo(), "UTF-8"));
		}
		if (!fcptBean.getSummary().isEmpty()) {
			fcptBean.setEncodeString(tool.removeFromString(
					fcptBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(fcptBean.getSummary(), "UTF-8"));
		}
		if (!fcptBean.getPay_method().isEmpty()) {
			fcptBean.setEncodeString(tool.removeFromString(
					fcptBean.getEncodeString(), "&", "&", "startswith",
					"pay_method")
					+ "&pay_method="
					+ tool.textEncode(quickPayString, "UTF-8"));
		}

		String requestString = fcptBean.getEncodeString().replaceAll(
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

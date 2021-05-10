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
import com.mas.bean.CreateSingleHostingInstantTradeBean;
import com.mas.bean.MasBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateSingleHostingInstantTrade extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateSingleHostingInstantTrade")
	public void doPost(@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("cihctBean") CreateSingleHostingInstantTradeBean cihctBean,
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
		if (!cihctBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + cihctBean.getOut_trade_no());
		}
		if (!cihctBean.getOut_trade_code().isEmpty()) {
			inputString.append(",out_trade_code="
					+ cihctBean.getOut_trade_code());
		}
		if (!cihctBean.getGift_money().isEmpty()) {
			inputString.append(",gift_money="
					+ cihctBean.getGift_money());
		}
		if (!cihctBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + cihctBean.getSummary());
		}
		if (!cihctBean.getTrade_close_time().isEmpty()) {
			inputString.append(",trade_close_time="
					+ cihctBean.getTrade_close_time());
		}
		if (!cihctBean.getCan_repay_on_failed().isEmpty()) {
			inputString.append(",can_repay_on_failed="
					+ cihctBean.getCan_repay_on_failed());
		}
		if (!cihctBean.getSplit_list().isEmpty()) {
			inputString.append(",split_list=" + cihctBean.getSplit_list());
		}
		if (!cihctBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + cihctBean.getExtend_param());
		}
		if (!cihctBean.getPayer_identity_id().isEmpty()) {
			inputString.append(",payer_identity_id=" + cihctBean.getPayer_identity_id());
		}
		if (!cihctBean.getPayer_identity_type().isEmpty()) {
			inputString.append(",payer_identity_type="
					+ cihctBean.getPayer_identity_type());
		}
		if (!cihctBean.getPayer_account_identity().isEmpty()) {
			inputString.append(",payer_account_identity="
					+ cihctBean.getPayer_account_identity());
		}
		if (!cihctBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_id="
					+ cihctBean.getGoods_id());
		}
		if (!cihctBean.getRelate_out_trade_no().isEmpty()) {
			inputString.append(",relate_out_trade_no="
					+ cihctBean.getRelate_out_trade_no());
		}
		if (!cihctBean.getCreditor_info_list().isEmpty()) {
			inputString.append(",creditor_info_list="
					+ cihctBean.getCreditor_info_list());
		}
		if (!cihctBean.getPayer_ip().isEmpty()) {
			inputString.append(",payer_ip=" + cihctBean.getPayer_ip());
		}
		String quickPayString = null;
		if (!cihctBean.getPay_method().isEmpty()
				&& cihctBean.getPay_method().startsWith("quick_pay")) {
			Tools tool = new Tools();
			quickPayString = tool.quickPayEncrypt(cihctBean.getPay_method(),
					masBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		} else {
			quickPayString = cihctBean.getPay_method().replaceAll(",",
					"replaceFlag");
			inputString.append(",pay_method=" + quickPayString);
		}
		if (!cihctBean.getIs_safe_card_support().isEmpty()) {
			inputString.append(",is_safe_card_support="
					+ cihctBean.getIs_safe_card_support());
		}
		if (!cihctBean.getCollect_trade_type().isEmpty()) {
			inputString.append(",collect_trade_type="
					+ cihctBean.getCollect_trade_type());
		}
		if (!cihctBean.getPayer_fee().isEmpty()) {
			inputString.append(",payer_fee=" + cihctBean.getPayer_fee());
		}
		if (!cihctBean.getCredit_amount().isEmpty()) {
			inputString.append(",credit_amount=" + cihctBean.getCredit_amount());
		}
		if (!cihctBean.getPayee_fee().isEmpty()) {
			inputString.append(",payee_fee=" + cihctBean.getPayee_fee());
		}
		if (!cihctBean.getPayee_identity_id().isEmpty()) {
			inputString.append(",payee_identity_id="
					+ cihctBean.getPayee_identity_id());
		}
		if (!cihctBean.getPayee_identity_type().isEmpty()) {
			inputString.append(",payee_identity_type="
					+ cihctBean.getPayee_identity_type());
		}
		if (!cihctBean.getPayee_account_type().isEmpty()) {
			inputString.append(",payee_account_type="
					+ cihctBean.getPayee_account_type());
		}
		if (!cihctBean.getPayee_account_identity().isEmpty()) {
			inputString.append(",payee_account_identity="
					+ cihctBean.getPayee_account_identity());
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

		cihctBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			cihctBean.setEncodeString(tool.removeFromString(
					cihctBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			cihctBean.setEncodeString(tool.removeFromString(
					cihctBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!cihctBean.getSummary().isEmpty()) {
			cihctBean.setEncodeString(tool.removeFromString(
					cihctBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(cihctBean.getSummary(), "UTF-8"));
		}
		if (!cihctBean.getPay_method().isEmpty()) {
			cihctBean.setEncodeString(tool.removeFromString(
					cihctBean.getEncodeString(), "&", "&", "startswith",
					"pay_method")
					+ "&pay_method="
					+ tool.textEncode(quickPayString, "UTF-8"));
		}

		String requestString = cihctBean.getEncodeString().replaceAll(
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

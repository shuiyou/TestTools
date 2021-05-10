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
import com.mas.bean.CreateSingleHostingPayToCardTradeBean;
import com.mas.bean.MasBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateSingleHostingPayToCardTrade extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateSingleHostingPayToCardTrade")
	public void doPost(
			@ModelAttribute("masBaseBean") MasBaseBean masBaseBean,
			@ModelAttribute("cshpctBean") CreateSingleHostingPayToCardTradeBean cshpctBean,
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
		if (!cshpctBean.getOut_trade_no().isEmpty()) {
			inputString.append(",out_trade_no=" + cshpctBean.getOut_trade_no());
		}
		if (!cshpctBean.getOut_trade_code().isEmpty()) {
			inputString.append(",out_trade_code="
					+ cshpctBean.getOut_trade_code());
		}
		if (!cshpctBean.getTrade_related_no().isEmpty()) {
			inputString.append(",trade_related_no="
					+ cshpctBean.getTrade_related_no());
		}
		if (!cshpctBean.getAmount().isEmpty()) {
			inputString.append(",amount=" + cshpctBean.getAmount());
		}
		if (!cshpctBean.getPayto_type().isEmpty()) {
			inputString.append(",payto_type=" + cshpctBean.getPayto_type());
		}
		if (!cshpctBean.getSummary().isEmpty()) {
			inputString.append(",summary=" + cshpctBean.getSummary());
		}
		if (!cshpctBean.getExtend_param().isEmpty()) {
			inputString.append(",extend_param=" + cshpctBean.getExtend_param());
		}
		if (!cshpctBean.getGoods_id().isEmpty()) {
			inputString.append(",goods_id=" + cshpctBean.getGoods_id());
		}		
		if (!cshpctBean.getUser_ip().isEmpty()) {
			inputString.append(",user_ip=" + cshpctBean.getUser_ip());
		}
		String collectMethodString = null;
		if (!cshpctBean.getCollect_method().isEmpty()
				&& cshpctBean.getCollect_method().startsWith("native_card")) {
			Tools tool = new Tools();
			collectMethodString = tool.collectMethodEncrypt(cshpctBean.getCollect_method(),
					masBaseBean.getEncryptkey()).replaceAll(",", "replaceFlag");
			inputString.append(",collect_method=" + collectMethodString);
		} else {
			collectMethodString = cshpctBean.getCollect_method().replaceAll(",",
					"replaceFlag");
			inputString.append(",collect_method=" + collectMethodString);
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

		cshpctBean.setEncodeString(sortString);
		if (!masBaseBean.getNotify_url().isEmpty()) {
			cshpctBean.setEncodeString(tool.removeFromString(
					cshpctBean.getEncodeString(), "&", "&", "startswith",
					"notify_url")
					+ "&notify_url="
					+ tool.textEncode(masBaseBean.getNotify_url(), "UTF-8"));
		}
		if (!masBaseBean.getReturn_url().isEmpty()) {
			cshpctBean.setEncodeString(tool.removeFromString(
					cshpctBean.getEncodeString(), "&", "&", "startswith",
					"return_url")
					+ "&return_url="
					+ tool.textEncode(masBaseBean.getReturn_url(), "UTF-8"));
		}
		if (!cshpctBean.getSummary().isEmpty()) {
			cshpctBean.setEncodeString(tool.removeFromString(
					cshpctBean.getEncodeString(), "&", "&", "startswith",
					"summary")
					+ "&summary="
					+ tool.textEncode(cshpctBean.getSummary(), "UTF-8"));
		}
		if (!cshpctBean.getCollect_method().isEmpty()) {
			cshpctBean.setEncodeString(tool.removeFromString(
					cshpctBean.getEncodeString(), "&", "&", "startswith",
					"collect_method")
					+ "&collect_method="
					+ tool.textEncode(collectMethodString, "UTF-8"));
		}

		String requestString = cshpctBean.getEncodeString().replaceAll(
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

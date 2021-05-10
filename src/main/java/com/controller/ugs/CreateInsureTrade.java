package com.controller.ugs;

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
import com.ugs.bean.CreateInsureTradeBean;
import com.ugs.bean.UgsBaseBean;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class CreateInsureTrade extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CreateInsureTrade")
	public void doPost(@ModelAttribute("ugsBaseBean") UgsBaseBean ugsBaseBean,
			@ModelAttribute("citBean") CreateInsureTradeBean citBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ugsBaseBean.set_input_charset(request.getParameter("_input_charset"));

		StringBuilder inputString = new StringBuilder("");
		if (!ugsBaseBean.getService().isEmpty()) {
			inputString.append("service=" + ugsBaseBean.getService());
		}
		if (!ugsBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + ugsBaseBean.getVersion());
		}
		if (!ugsBaseBean.getRequest_time().isEmpty()) {
			inputString
					.append(",request_time=" + ugsBaseBean.getRequest_time());
		}
		if (!ugsBaseBean.getPartner_id().isEmpty()) {
			inputString.append(",partner_id=" + ugsBaseBean.getPartner_id());
		}
		if (!ugsBaseBean.get_input_charset().isEmpty()) {
			inputString.append(",_input_charset="
					+ ugsBaseBean.get_input_charset());
		}
		if (!ugsBaseBean.getSign_type().isEmpty()) {
			inputString.append(",sign_type=" + ugsBaseBean.getSign_type());
		}
		if (!ugsBaseBean.getSign_version().isEmpty()) {
			inputString
					.append(",sign_version=" + ugsBaseBean.getSign_version());
		}
		if (!ugsBaseBean.getEncrypt_version().isEmpty()) {
			inputString.append(",encrypt_version="
					+ ugsBaseBean.getEncrypt_version());
		}
		if (!ugsBaseBean.getNotify_url().isEmpty()) {
			inputString.append(",notify_url=" + ugsBaseBean.getNotify_url());
		}
		if (!ugsBaseBean.getReturn_url().isEmpty()) {
			inputString.append(",return_url=" + ugsBaseBean.getReturn_url());
		}
		
		StringBuilder biz = new StringBuilder("");
		if (!citBean.getOut_trade_no().isEmpty()) {
			biz.append("\"out_trade_no\":\"" + citBean.getOut_trade_no()+ "\",");
		}
		if (!citBean.getSummary().isEmpty()) {
			biz.append("\"summary\":\"" + citBean.getSummary() + "\",");
		}
		if (!citBean.getPayer_identity_id().isEmpty()) {
			biz.append("\"payer_identity_id\":\"" + citBean.getPayer_identity_id()+ "\",");
		}
		if (!citBean.getPayer_identity_type().isEmpty()) {
			biz.append("\"payer_identity_type\":\"" + citBean.getPayer_identity_type()+ "\",");
		}
		if (!citBean.getPayer_ip().isEmpty()) {
			biz.append("\"payer_ip\":\"" + citBean.getPayer_ip()+ "\",");
		}
		if (!citBean.getAmount().isEmpty()) {
			biz.append("\"amount\":\"" + citBean.getAmount()+ "\",");
		}
		if (!citBean.getPay_method().isEmpty()) {
			String tmp = citBean.getPay_method().replace(",", "replaceFlag");

			biz.append("\"pay_method\":\"" + tmp + "\",");	
		}
		if (!citBean.getPayee_identity_id().isEmpty()) {
			biz.append("\"payee_identity_id\":\"" + citBean.getPayee_identity_id()+ "\",");
		}
		if (!citBean.getPayee_identity_type().isEmpty()) {
			biz.append("\"payee_identity_type\":\"" + citBean.getPayee_identity_type()+ "\",");
		}
		if (!citBean.getPayee_account_type().isEmpty()) {
			biz.append("\"payee_account_type\":\"" + citBean.getPayee_account_type()+ "\",");
		}
		if (!citBean.getTrade_close_time().isEmpty()) {
			biz.append("\"trade_close_time\":\"" + citBean.getTrade_close_time()+ "\",");
		}
		if (!citBean.getExtend_param().isEmpty()) {
			biz.append("\"extend_param\":\"" + citBean.getExtend_param()+ "\"");
		}
		
		String[] bizsplitString = biz.toString().split(",");
	
		Tools tool = new Tools();
		String bizsortString = tool.sortStringWithSeparator(bizsplitString, ",");
	
		String  bizsortString1=bizsortString.replace("replaceFlag", ",");
		
		
	
		inputString.append(",biz_content={replaceFlag}");
		
		
		String[] splitString = inputString.toString().split(",");

		String sortString1 = tool.sortStringWithSeparator(splitString, "&");
		String sortString=sortString1.replace("replaceFlag",bizsortString1);
		String signString = tool.removeFromString(sortString, "&", "&",
				"startswith", "sign").replaceAll("replaceFlag", ",");
		System.out.println(signString);

		String sign = null;
		RSAUtil rsa = new RSAUtil();
		MD5Util md5 = new MD5Util();
		if (ugsBaseBean.getSign_type().equals("RSA")) {
			rsa.setPrivateKey(ugsBaseBean.getRsakey());
			try {
				sign = rsa.sign(signString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ugsBaseBean.getSign_type().equals("MD5")) {
			String md5SignString = signString + ugsBaseBean.getMd5key();
			sign = md5.getMD5(md5SignString, "UTF-8");
		}

		citBean.setEncodeString(sortString);
//		if (!ugsBaseBean.getNotify_url().isEmpty()) {
//			citBean.setEncodeString(tool.removeFromString(
//					citBean.getEncodeString(), "&", "&", "startswith",
//					"notify_url")
//					+ "&notify_url="
//					+ tool.textEncode(ugsBaseBean.getNotify_url(), "UTF-8"));
//		}
//		if (!ugsBaseBean.getReturn_url().isEmpty()) {
//			citBean.setEncodeString(tool.removeFromString(
//					citBean.getEncodeString(), "&", "&", "startswith",
//					"return_url")
//					+ "&return_url="
//					+ tool.textEncode(ugsBaseBean.getReturn_url(), "UTF-8"));
//		}

		String requestString = citBean.getEncodeString().replaceAll(
				"replaceFlag", ",")
				+ "&sign=" + sign;
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(ugsBaseBean.getUrl(), requestString), "UTF-8");
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

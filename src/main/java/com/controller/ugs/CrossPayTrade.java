package com.controller.ugs;

import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;
import com.ugs.bean.CrossPayTradeBean;
import com.ugs.bean.UgsBaseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hanxiaodi on 18/5/31.
 */
@Controller
public class CrossPayTrade extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CrossPayTrade")
	public void doPost(@ModelAttribute("ugsBaseBean") UgsBaseBean ugsBaseBean,
			@ModelAttribute("cptBean") CrossPayTradeBean cptBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
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
		if (!cptBean.getOut_trade_no().isEmpty()) {
			biz.append("\"out_trade_no\":\"" + cptBean.getOut_trade_no()+ "\",");
		}

		if (!cptBean.getOut_pay_no().isEmpty()) {
			biz.append("\"out_pay_no\":\"" + cptBean.getOut_pay_no() + "\",");
		}
		if (!cptBean.getPayer_ip().isEmpty()) {
			biz.append("\"payer_ip\":\"" + cptBean.getPayer_ip()+ "\",");
		}
		if (!cptBean.getPay_method().isEmpty()) {
			biz.append("\"pay_method\":\"" + cptBean.getPay_method()+ "\"");
		}



		String BizContentString = biz.toString();

		Tools tool = new Tools();

		inputString.append(",biz_content={replaceFlag}");

		String[] splitString = inputString.toString().split(",");

		String sortString1 = tool.sortStringWithSeparator(splitString, "&");
		String sortString=sortString1.replace("replaceFlag",BizContentString);
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

		cptBean.setEncodeString(sortString);

		String requestString = cptBean.getEncodeString().replaceAll(
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

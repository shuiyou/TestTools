package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2c.bean.B2CBaseBean;
import com.b2c.bean.CreateB2COrderBean;
import com.b2c.bean.SettleB2COrderBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

/*
 * **
 * **
 */

@Controller
public class SettleB2COrder extends HttpServlet {
	
/*
 * **
 * **
 */
		
	private static final long serialVersionUID = 1L;
	@RequestMapping(value = "SettleB2COrder")
	public void doPost(@ModelAttribute("b2cBaseBean") B2CBaseBean b2cBaseBean,
			@ModelAttribute("sboBean") SettleB2COrderBean sboBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			b2cBaseBean.set_input_charset(request.getParameter("_input_charset"));
			StringBuilder inputString = new StringBuilder("");
			
			if (!b2cBaseBean.getService().isEmpty()) {
				inputString.append("service=" + b2cBaseBean.getService());
			}
			if (!b2cBaseBean.getVersion().isEmpty()) {
				inputString.append(",version=" + b2cBaseBean.getVersion());
			}
			if (!b2cBaseBean.getRequest_time().isEmpty()) {
				inputString
						.append(",request_time=" + b2cBaseBean.getRequest_time());
			}
			if (!b2cBaseBean.getPartner_id().isEmpty()) {
				inputString.append(",partner_id=" + b2cBaseBean.getPartner_id());
			}
			if (!b2cBaseBean.get_input_charset().isEmpty()) {
				inputString.append(",_input_charset="
						+ b2cBaseBean.get_input_charset());
			}
			if (!b2cBaseBean.getSign_type().isEmpty()) {
				inputString.append(",sign_type=" + b2cBaseBean.getSign_type());
			}
			if (!b2cBaseBean.getSign_version().isEmpty()) {
				inputString
						.append(",sign_version=" + b2cBaseBean.getSign_version());
			}
			if (!b2cBaseBean.getEncrypt_version().isEmpty()) {
				inputString.append(",encrypt_version="
						+ b2cBaseBean.getEncrypt_version());
			}
			if (!b2cBaseBean.getNotify_url().isEmpty()) {
				inputString.append(",notify_url=" + b2cBaseBean.getNotify_url());
			}
			if (!b2cBaseBean.getReturn_url().isEmpty()) {
				inputString.append(",return_url=" + b2cBaseBean.getReturn_url());
			}

			if (!sboBean.getOut_request_no().isEmpty()) {
				inputString.append(",out_request_no=" + sboBean.getOut_request_no());
			}
			
			if (!sboBean.getOut_trade_no().isEmpty()) {
				inputString.append(",out_trade_no=" + sboBean.getOut_trade_no());
			}
			
			
			if (!sboBean.getExtend_param().isEmpty()) {
				inputString.append(",extend_param=" + sboBean.getExtend_param());
			}

			if (!sboBean.getSplit_list().isEmpty()) {
				inputString.append(",split_list=" + sboBean.getSplit_list());
			}

		String riskInfo = null;
		if (!b2cBaseBean.getRisk_info().isEmpty()) {
			riskInfo = b2cBaseBean.getRisk_info().replaceAll(",","replaceFlag");
			inputString.append(",risk_info=" + riskInfo);
		}
		String deviceInfo = null;
		if (!b2cBaseBean.getDevice_info().isEmpty()) {
			deviceInfo = b2cBaseBean.getDevice_info().replaceAll(",","replaceFlag");
			inputString.append(",device_info=" + deviceInfo);
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
			if (b2cBaseBean.getSign_type().equals("RSA")) {
				rsa.setPrivateKey(b2cBaseBean.getRsakey());
				try {
					sign = rsa.sign(signString);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (b2cBaseBean.getSign_type().equals("MD5")) {
				String md5SignString = signString + b2cBaseBean.getMd5key();
				sign = md5.getMD5(md5SignString, "UTF-8");
			}

			sboBean.setEncodeString(sortString);
			if (!b2cBaseBean.getNotify_url().isEmpty()) {
				sboBean.setEncodeString(tool.removeFromString(
						sboBean.getEncodeString(), "&", "&", "startswith",
						"notify_url")
						+ "&notify_url="
						+ tool.textEncode(b2cBaseBean.getNotify_url(), "UTF-8"));
			}
			if (!b2cBaseBean.getReturn_url().isEmpty()) {
				sboBean.setEncodeString(tool.removeFromString(
						sboBean.getEncodeString(), "&", "&", "startswith",
						"return_url")
						+ "&return_url="
						+ tool.textEncode(b2cBaseBean.getReturn_url(), "UTF-8"));
			}
			if (!sboBean.getSplit_list().isEmpty()) {
				sboBean.setEncodeString(tool.removeFromString(
						sboBean.getEncodeString(), "&", "&", "startswith",
						"split_list")
						+ "&split_list="
						+ tool.textEncode(sboBean.getSplit_list(), "UTF-8"));
			}
			if (!sboBean.getExtend_param().isEmpty()) {
				sboBean.setEncodeString(tool.removeFromString(
						sboBean.getEncodeString(), "&", "&", "startswith",
						"extend_param")
						+ "&extend_param="
						+ tool.textEncode(sboBean.getExtend_param(), "UTF-8"));
			}
		if (!b2cBaseBean.getRisk_info().isEmpty()) {
			sboBean.setEncodeString(tool.removeFromString(
					sboBean.getEncodeString(), "&", "&", "startswith",
					"risk_info")
					+ "&risk_info="
					+ tool.textEncode(riskInfo, "UTF-8"));
		}

			String requestString = sboBean.getEncodeString().replaceAll(
					"replaceFlag", ",")
					+ "&sign=" + tool.textEncode(sign, "UTF-8");
			System.out.println("????????????: " + requestString);

			String responseString = tool.textDncode(
					tool.post(b2cBaseBean.getUrl(), requestString), "UTF-8");
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


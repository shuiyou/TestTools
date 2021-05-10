package com.controller.wgs;

import com.common.AesUtil;
import com.common.Tools;
import com.meidusa.fastjson.JSONObject;
import com.wgsBean.LoginCheckPasswordBean;
import com.wgsBean.WgsBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hanxiaodi on 17/11/17.
 */
@Controller
public class LoginCheckPassword extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "LoginCheckPassword")
	public void doPost(@ModelAttribute("wgsBaseBean") WgsBean wgsBaseBean,
			@ModelAttribute("lcpBean") LoginCheckPasswordBean lcpBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{


		StringBuilder inputString = new StringBuilder("");
		StringBuilder biz_content = new StringBuilder("");

		if (!wgsBaseBean.getPlatform().isEmpty()) {
			inputString.append("platform=" + wgsBaseBean.getPlatform());
		}
		if (!wgsBaseBean.getVersion().isEmpty()) {
			inputString.append(",version=" + wgsBaseBean.getVersion());
		}
		if (!wgsBaseBean.getService().isEmpty()) {
			inputString.append(",service=" + wgsBaseBean.getService());
		}
		if (!wgsBaseBean.getLang().isEmpty()) {
			inputString.append(",lang=" + wgsBaseBean.getLang());
		}
		String encryptPassword = AesUtil.encrypt(lcpBean.getPassword(),lcpBean.getSalt());
		HashMap<String,String> hashMap = new HashMap<String, String>(  );
		hashMap.put( "mobile", lcpBean.getMobile());
		hashMap.put( "device_identity", lcpBean.getDevice_identity());
		hashMap.put( "salt_id", lcpBean.getSalt_id());
		hashMap.put( "salt", lcpBean.getSalt());
		hashMap.put( "password", encryptPassword);



		String[] splitString = inputString.toString().split(",");

		Tools tool = new Tools();
		String sortString = tool.sortStringWithSeparator(splitString, "&");
		String Register = "";
		Register = JSONObject.toJSONString( hashMap );







		String requestString = sortString+"&biz_content="+Register;
		System.out.println("请求报文: " + requestString);

		String responseString = tool.textDncode(
				tool.post(wgsBaseBean.getUrl(), requestString), "UTF-8");
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

package com.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wos.bean.PersonalMemberInfoAddBean;
import com.common.MD5Util;
import com.common.RSAUtil;
import com.common.Tools;

@Controller
public class PersonalMemberInfoAdd extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "PersonalMemberInfoAdd")
	public void doPost(@ModelAttribute("personalMemberInfoAdd") PersonalMemberInfoAddBean personalMemberInfoAdd,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Tools tool = new Tools();
		RSAUtil rsa= new RSAUtil();
		MD5Util md5 =  new MD5Util();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", personalMemberInfoAdd.getService());
		inputmap.put("version", personalMemberInfoAdd.getVersion());
		inputmap.put("request_time", personalMemberInfoAdd.getRequest_time());
		inputmap.put("partner_id", personalMemberInfoAdd.getPartner_id());
		inputmap.put("sign_type", personalMemberInfoAdd.getSign_type());
		inputmap.put("sign_version", personalMemberInfoAdd.getSign_version());
		inputmap.put("platform_type", personalMemberInfoAdd.getPlatform_type());
		inputmap.put("login_name_type", personalMemberInfoAdd.getLogin_name_type());
		inputmap.put("login_name", personalMemberInfoAdd.getLogin_name());
		inputmap.put("login_pwd", personalMemberInfoAdd.getLogin_pwd());
		
		if(!personalMemberInfoAdd.getInvite_code().isEmpty())
		{
			inputmap.put("invite_code", personalMemberInfoAdd.getInvite_code());
		}
		
		
		String requestString =tool.sortMapWithSeparator(inputmap, "&");
		String signString = requestString;
		signString  =tool.removeFromString(signString,"&","&","startswith","sign");
	
		//加签
		signString = signString + personalMemberInfoAdd.MD5KEY;

		//对 pwd 进行 SHA256 加密
		String pwd = tool.sha256Encrypt(personalMemberInfoAdd.getLogin_pwd());
		
		//对sha256密码进行RSA 加密
		String rsapwd = rsa.encrypt(pwd,personalMemberInfoAdd.RSAPUBLICKEY);
		
		//加密后的密码代替请求报文中的密码
		signString = signString.replace(personalMemberInfoAdd.getLogin_pwd(), rsapwd);
		
		//md5加密
		String signMsg = md5.getMD5(signString,"UTF-8");
		
		//请求参数加上加密窜
		requestString = requestString +"&sign=" + signMsg;
				
		//对密码文pwd 进行 encode
		String newPwd = tool.encode(rsapwd);
		
		//请求参数中用密文pwd 取代 原pwd
		requestString = requestString.replace(personalMemberInfoAdd.getLogin_pwd(), newPwd);
		
		String responseString =tool.textDncode(
				tool.post(personalMemberInfoAdd.getUrl(), requestString), "UTF-8");
		
		//返回报文 Decode
		responseString =tool.getDecode(responseString.substring(responseString.indexOf('=')+1, responseString.indexOf('&')));
		
		System.out.println("响应报文: " + responseString);

		request.setAttribute("response", responseString);
		request.setAttribute("request", requestString);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

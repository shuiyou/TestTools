package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wos.bean.TradeDepositAddBean;
import com.common.Tools;

@Controller
public class TradeDepositAdd extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "TradeDepositAdd")
	public void doPost(@ModelAttribute("tradeDepositAdd") TradeDepositAddBean tradeDepositAdd,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", tradeDepositAdd.getService());
		inputmap.put("version", tradeDepositAdd.getVersion());
		inputmap.put("request_time", tradeDepositAdd.getRequest_time());
		inputmap.put("partner_id", tradeDepositAdd.getPartner_id());
		inputmap.put("sign_type", tradeDepositAdd.getSign_type());
		inputmap.put("sign_version", tradeDepositAdd.getSign_version());
		inputmap.put("platform_type", tradeDepositAdd.getPlatform_type());
		inputmap.put("member_id", tradeDepositAdd.getMember_id());
		inputmap.put("amount", tradeDepositAdd.getAmount());
		if(!tradeDepositAdd.getReturn_url().isEmpty())
		{
			inputmap.put("return_url", tradeDepositAdd.getReturn_url());
		}
		if(!tradeDepositAdd.getUser_fee().isEmpty())
		{
			inputmap.put("user_fee", tradeDepositAdd.getUser_fee());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, tradeDepositAdd);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

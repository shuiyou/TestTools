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
import com.wos.bean.TradeWithdrawAddBean;
import com.common.Tools;

@Controller
public class TradeWithdrawAdd extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "TradeWithdrawAdd")
	public void doPost(@ModelAttribute("tradWithdrawAdd") TradeWithdrawAddBean tradeWithdrawAdd,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", tradeWithdrawAdd.getService());
		inputmap.put("version", tradeWithdrawAdd.getVersion());
		inputmap.put("request_time", tradeWithdrawAdd.getRequest_time());
		inputmap.put("partner_id", tradeWithdrawAdd.getPartner_id());
		inputmap.put("sign_type", tradeWithdrawAdd.getSign_type());
		inputmap.put("sign_version", tradeWithdrawAdd.getSign_version());
		inputmap.put("platform_type", tradeWithdrawAdd.getPlatform_type());
		inputmap.put("member_id", tradeWithdrawAdd.getMember_id());
		inputmap.put("amount", tradeWithdrawAdd.getAmount());
		if(!tradeWithdrawAdd.getReturn_url().isEmpty())
		{
			inputmap.put("return_url", tradeWithdrawAdd.getReturn_url());
		}
		if(!tradeWithdrawAdd.getUser_fee().isEmpty())
		{
			inputmap.put("user_fee", tradeWithdrawAdd.getUser_fee());
		}
		if(!tradeWithdrawAdd.getSummary().isEmpty())
		{
			inputmap.put("summary", tradeWithdrawAdd.getSummary());
		}
		
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, tradeWithdrawAdd);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

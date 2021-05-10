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
import com.common.Tools;
import com.wos.bean.TradeRepaymentAddBean;

@Controller
public class TradeRepaymentAdd extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "TradeRepaymentAdd")
	public void doPost(@ModelAttribute("tradeRepaymentAdd") TradeRepaymentAddBean tradeRepaymentAdd,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", tradeRepaymentAdd.getService());
		inputmap.put("version", tradeRepaymentAdd.getVersion());
		inputmap.put("request_time", tradeRepaymentAdd.getRequest_time());
		inputmap.put("partner_id", tradeRepaymentAdd.getPartner_id());
		inputmap.put("sign_type", tradeRepaymentAdd.getSign_type());
		inputmap.put("sign_version", tradeRepaymentAdd.getSign_version());
		inputmap.put("platform_type", tradeRepaymentAdd.getPlatform_type());
		inputmap.put("member_id", tradeRepaymentAdd.getMember_id());
		inputmap.put("amount", tradeRepaymentAdd.getAmount());
		inputmap.put("return_url", tradeRepaymentAdd.getReturn_url());
		inputmap.put("bill_id", tradeRepaymentAdd.getBill_id());
		
		if(!tradeRepaymentAdd.getSummary().isEmpty())
		{
			inputmap.put("summary", tradeRepaymentAdd.getSummary());
		}
		if(!tradeRepaymentAdd.getClose_time().isEmpty())
		{
			inputmap.put("close_time", tradeRepaymentAdd.getClose_time());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, tradeRepaymentAdd);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

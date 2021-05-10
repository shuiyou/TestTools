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
import com.wos.bean.TradeInvestAddBean;
import com.common.Tools;

@Controller
public class TradeInvestAdd extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "TradeInvestAdd")
	public void doPost(@ModelAttribute("tradeInvestAdd") TradeInvestAddBean tradeInvestAdd,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", tradeInvestAdd.getService());
		inputmap.put("version", tradeInvestAdd.getVersion());
		inputmap.put("request_time", tradeInvestAdd.getRequest_time());
		inputmap.put("partner_id", tradeInvestAdd.getPartner_id());
		inputmap.put("sign_type", tradeInvestAdd.getSign_type());
		inputmap.put("sign_version", tradeInvestAdd.getSign_version());
		inputmap.put("platform_type", tradeInvestAdd.getPlatform_type());
		inputmap.put("member_id", tradeInvestAdd.getMember_id());
		inputmap.put("amount", tradeInvestAdd.getAmount());
		inputmap.put("return_url", tradeInvestAdd.getReturn_url());
		inputmap.put("goods_id", tradeInvestAdd.getGoods_id());
		
		if(!tradeInvestAdd.getSummary().isEmpty())
		{
			inputmap.put("summary", tradeInvestAdd.getSummary());
		}
		if(!tradeInvestAdd.getClose_time().isEmpty())
		{
			inputmap.put("close_time", tradeInvestAdd.getClose_time());
		}
		if(!tradeInvestAdd.getAddress_id().isEmpty())
		{
			inputmap.put("address_id", tradeInvestAdd.getAddress_id());
		}
		if(!tradeInvestAdd.getRedpacket_id().isEmpty())
		{
			inputmap.put("redpacket_id", tradeInvestAdd.getRedpacket_id());
		}
		
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, tradeInvestAdd);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

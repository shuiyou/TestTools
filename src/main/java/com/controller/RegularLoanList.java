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
import com.wos.bean.RegularLoanListBean;
import com.common.Tools;

@Controller
public class RegularLoanList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "RegularLoanList")
	public void doPost(@ModelAttribute("regularLoanList") RegularLoanListBean regularLoanList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", regularLoanList.getService());
		inputmap.put("version",regularLoanList.getVersion());
		inputmap.put("request_time", regularLoanList.getRequest_time());
		inputmap.put("partner_id", regularLoanList.getPartner_id());
		inputmap.put("sign_type", regularLoanList.getSign_type());
		inputmap.put("sign_version", regularLoanList.getSign_version());
		inputmap.put("platform_type", regularLoanList.getPlatform_type());
		inputmap.put("member_id", regularLoanList.getMember_id());
		
		if(!regularLoanList.getDebt_status().isEmpty())
		{
			inputmap.put("debt_status", regularLoanList.getDebt_status());
		}
		if(!regularLoanList.getGoods_id().isEmpty())
		{
			inputmap.put("goods_id", regularLoanList.getGoods_id());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, regularLoanList);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

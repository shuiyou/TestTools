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
import com.wos.bean.RegularRepaymentPlanListBean;
import com.common.Tools;

@Controller
public class RegularRepaymentPlanList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "RegularRepaymentPlanList")
	public void doPost(@ModelAttribute("regularRepaymentPlanList") RegularRepaymentPlanListBean regularRepaymentPlanList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", regularRepaymentPlanList.getService());
		inputmap.put("version",regularRepaymentPlanList.getVersion());
		inputmap.put("request_time", regularRepaymentPlanList.getRequest_time());
		inputmap.put("partner_id", regularRepaymentPlanList.getPartner_id());
		inputmap.put("sign_type", regularRepaymentPlanList.getSign_type());
		inputmap.put("sign_version", regularRepaymentPlanList.getSign_version());
		inputmap.put("platform_type", regularRepaymentPlanList.getPlatform_type());
		inputmap.put("member_id", regularRepaymentPlanList.getMember_id());
		
		if(!regularRepaymentPlanList.getBill_status().isEmpty())
		{
			inputmap.put("debt_status", regularRepaymentPlanList.getBill_status());
		}
		if(!regularRepaymentPlanList.getBill_begin_time().isEmpty())
		{
			inputmap.put("goods_id", regularRepaymentPlanList.getBill_begin_time());
		}
		if(!regularRepaymentPlanList.getBill_end_time().isEmpty())
		{
			inputmap.put("goods_id", regularRepaymentPlanList.getBill_end_time());
		}
		if(!regularRepaymentPlanList.getGoods_id().isEmpty())
		{
			inputmap.put("goods_id", regularRepaymentPlanList.getGoods_id());
		}
		if(!regularRepaymentPlanList.getRepayment_begin_time().isEmpty())
		{
			inputmap.put("goods_id", regularRepaymentPlanList.getRepayment_begin_time());
		}
		if(!regularRepaymentPlanList.getRepayment_end_time().isEmpty())
		{
			inputmap.put("goods_id", regularRepaymentPlanList.getRepayment_end_time());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, regularRepaymentPlanList);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

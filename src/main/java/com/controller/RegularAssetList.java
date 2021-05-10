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
import com.wos.bean.RegularAssetListBean;
import com.common.Tools;

@Controller
public class RegularAssetList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "RegularAssetList")
	public void doPost(@ModelAttribute("regularAssetList") RegularAssetListBean regularAssetList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", regularAssetList.getService());
		inputmap.put("version",regularAssetList.getVersion());
		inputmap.put("request_time", regularAssetList.getRequest_time());
		inputmap.put("partner_id", regularAssetList.getPartner_id());
		inputmap.put("sign_type", regularAssetList.getSign_type());
		inputmap.put("sign_version", regularAssetList.getSign_version());
		inputmap.put("platform_type", regularAssetList.getPlatform_type());
		inputmap.put("member_id", regularAssetList.getMember_id());
		inputmap.put("status", regularAssetList.getStatus());
		if(!regularAssetList.getOrder_column().isEmpty())
		{
			inputmap.put("order_column", regularAssetList.getOrder_column());
		}
		if(!regularAssetList.getOrder_direction().isEmpty())
		{
			inputmap.put("order_direction", regularAssetList.getOrder_direction());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, regularAssetList);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

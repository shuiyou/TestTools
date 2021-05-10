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

import com.wos.bean.CouponListBean;
import com.common.Tools;

@Controller
public class CouponList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CouponList")
	public void doPost(@ModelAttribute("couponList") CouponListBean couponList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", couponList.getService());
		inputmap.put("version",couponList.getVersion());
		inputmap.put("request_time", couponList.getRequest_time());
		inputmap.put("partner_id", couponList.getPartner_id());
		inputmap.put("sign_type", couponList.getSign_type());
		inputmap.put("sign_version", couponList.getSign_version());
		inputmap.put("platform_type", couponList.getPlatform_type());
		inputmap.put("member_id", couponList.getMember_id());
		
		if(!couponList.getStatus().isEmpty())
		{
			inputmap.put("status", couponList.getStatus());
		}
		if(!couponList.getPage_no().isEmpty())
		{
			inputmap.put("page_no", couponList.getPage_no());
		}
		if(!couponList.getPage_size().isEmpty())
		{
			inputmap.put("page_size", couponList.getPage_size());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, couponList);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

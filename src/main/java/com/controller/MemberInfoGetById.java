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

import com.wos.bean.MemberInfoGetByIdBean;
import com.common.Tools;

@Controller
public class MemberInfoGetById extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "MemberInfoGetById")
	public void doPost(@ModelAttribute("memberInfoGetById") MemberInfoGetByIdBean memberInfoGetById,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", memberInfoGetById.getService());
		inputmap.put("version", memberInfoGetById.getVersion());
		inputmap.put("request_time", memberInfoGetById.getRequest_time());
		inputmap.put("partner_id", memberInfoGetById.getPartner_id());
		inputmap.put("sign_type", memberInfoGetById.getSign_type());
		inputmap.put("sign_version", memberInfoGetById.getSign_version());
		inputmap.put("platform_type", memberInfoGetById.getPlatform_type());
		inputmap.put("member_id", memberInfoGetById.getMember_id());
		if(!memberInfoGetById.getRequire_verify_infos().isEmpty())
		{
			inputmap.put("require_verify_infos", memberInfoGetById.getRequire_verify_infos());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, memberInfoGetById);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

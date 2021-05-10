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
import com.wos.bean.SiteMsgListBean;
import com.common.Tools;

@Controller
public class SiteUsrMsgList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "SiteUsrMsgList")
	public void doPost(@ModelAttribute("SiteUsrMsgList") SiteMsgListBean siteMsgList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", siteMsgList.getService());
		inputmap.put("version",siteMsgList.getVersion());
		inputmap.put("request_time", siteMsgList.getRequest_time());
		inputmap.put("partner_id", siteMsgList.getPartner_id());
		inputmap.put("sign_type", siteMsgList.getSign_type());
		inputmap.put("sign_version", siteMsgList.getSign_version());
		inputmap.put("platform_type", siteMsgList.getPlatform_type());
		inputmap.put("member_id", siteMsgList.getMember_id());
		
		if(!siteMsgList.getStatus().isEmpty())
		{
			inputmap.put("status", siteMsgList.getStatus());
		}
		if(!siteMsgList.getPage_no().isEmpty())
		{
			inputmap.put("page_no", siteMsgList.getPage_no());
		}
		if(!siteMsgList.getPage_size().isEmpty())
		{
			inputmap.put("page_size", siteMsgList.getPage_size());
		}
		
		if(!siteMsgList.getStart_time().isEmpty())
		{
			inputmap.put("start_time", siteMsgList.getStart_time());
		}
		if(!siteMsgList.getEnd_time().isEmpty())
		{
			inputmap.put("end_time", siteMsgList.getEnd_time());
		}
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, siteMsgList);
		
		
		
		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

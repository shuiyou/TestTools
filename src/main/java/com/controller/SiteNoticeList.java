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
import com.wos.bean.SiteBannerListBean;
import com.common.Tools;

@Controller
public class SiteNoticeList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "SiteNoticeList")
	public void doPost(@ModelAttribute("siteBannerList") SiteBannerListBean siteBannerList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", siteBannerList.getService());
		inputmap.put("version",siteBannerList.getVersion());
		inputmap.put("request_time", siteBannerList.getRequest_time());
		inputmap.put("partner_id", siteBannerList.getPartner_id());
		inputmap.put("sign_type", siteBannerList.getSign_type());
		inputmap.put("sign_version", siteBannerList.getSign_version());
		inputmap.put("platform_type", siteBannerList.getPlatform_type());
		inputmap.put("level", siteBannerList.getLevel());
		
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, siteBannerList);

		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

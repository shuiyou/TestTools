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
import com.wos.bean.GoodsInfoGetBean;
import com.common.Tools;

@Controller
public class GoodsInfoGet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "GoodsInfoGet")
	public void doPost(@ModelAttribute("goodsInfoGet") GoodsInfoGetBean goodsInfoGet,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", goodsInfoGet.getService());
		inputmap.put("version",goodsInfoGet.getVersion());
		inputmap.put("request_time", goodsInfoGet.getRequest_time());
		inputmap.put("partner_id", goodsInfoGet.getPartner_id());
		inputmap.put("sign_type", goodsInfoGet.getSign_type());
		inputmap.put("sign_version", goodsInfoGet.getSign_version());
		inputmap.put("platform_type", goodsInfoGet.getPlatform_type());
		inputmap.put("goods_id", goodsInfoGet.getGoods_id());
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, goodsInfoGet);

		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

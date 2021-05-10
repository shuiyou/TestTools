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

import com.wos.bean.CouponConsumableListBean;
import com.common.Tools;

@Controller
public class CouponConsumableList extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "CouponConsumableList")
	public void doPost(@ModelAttribute("couponConsumableList") CouponConsumableListBean couponConsumableList,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Tools tool = new Tools();
		Map<String,String> inputmap = new HashMap<String,String>();
		inputmap.put("service", couponConsumableList.getService());
		inputmap.put("version",couponConsumableList.getVersion());
		inputmap.put("request_time", couponConsumableList.getRequest_time());
		inputmap.put("partner_id", couponConsumableList.getPartner_id());
		inputmap.put("sign_type", couponConsumableList.getSign_type());
		inputmap.put("sign_version", couponConsumableList.getSign_version());
		inputmap.put("platform_type", couponConsumableList.getPlatform_type());
		inputmap.put("member_id", couponConsumableList.getMember_id());
		inputmap.put("goods_id", couponConsumableList.getGoods_id());
		inputmap.put("amount", couponConsumableList.getAmount());
		inputmap.put("trade_code", couponConsumableList.getTrade_code());
		
		//加MD5签名 并发送POST请求
		Map<String,String> responsemap = tool.GetResponseByMd5AndPost(inputmap, couponConsumableList);

		System.out.println("响应报文: " + responsemap.get("response"));

		request.setAttribute("response", responsemap.get("response"));
		request.setAttribute("request", responsemap.get("request"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
	}
	

}

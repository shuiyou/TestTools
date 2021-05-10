package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ajaxTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value="ajaxTest")
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("call success");
		String msg = "第一个AJAX小程序";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(msg);
	}
}

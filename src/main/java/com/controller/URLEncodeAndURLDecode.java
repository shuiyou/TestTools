package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Tools;

@Controller
public class URLEncodeAndURLDecode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "encode")
	public void encode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Tools tool = new Tools();
		String encodeContent = request.getParameter("encodeContent");
//		encodeContent = new String(encodeContent.getBytes("iso-8859-1"),
//				"UTF-8");
		String encodeString = tool.textEncode(encodeContent, "UTF-8");
		response.getWriter().write(encodeString);

	}

	@RequestMapping(value = "decode")
	public void decode(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Tools tool = new Tools();
		String decodeContent = request.getParameter("decodeContent");
//		decodeContent = new String(decodeContent.getBytes("iso-8859-1"),
//				"UTF-8");
		String decodeString = tool.textDncode(decodeContent, "UTF-8");
		response.getWriter().write(decodeString);

	}

}

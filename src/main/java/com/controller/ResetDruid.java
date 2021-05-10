package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Tools;

@Controller
public class ResetDruid extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "resetDruid")
	public void reset(HttpServletRequest request, HttpServletResponse response) {
		Tools tools = new Tools();
		StringBuilder result = new StringBuilder("");
		String urlList = request.getParameter("druid_url");
		List<String> list = new ArrayList<String>(Arrays.asList(urlList
				.split(",")));
		// System.out.println("list::::::" + list);
		int len = list.size();
		for (int i = 0; i < len; i++) {
			String url = list.get(i);
			String resetResponse = tools.post(url, "");
			// System.out.println(i+ "::::::" + resetResponse);
			if (resetResponse.contains("\"ResultCode\":1")) {
				result.append(url + "::: reset Success!<br/>");
			} else {
				result.append(url + "::: reset Fail!!!!!!<br/>");
			}

		}
		try {
			PrintWriter out = response.getWriter();
			out.write(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

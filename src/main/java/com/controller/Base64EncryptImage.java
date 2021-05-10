package com.controller;

import com.common.ImageBase64Util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by hanxiaodi on 18/4/4.
 */
@Controller
public class Base64EncryptImage extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "encryptImage")
	public void encrypt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String encryptImageContent = request.getParameter("encryptImageContent");
		String encryptKey = request.getParameter("encryptkey");
// 		String encryptKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgzUSRjJijlZudygIjXl9EzqbwQTdnZys88wt4OolaRo6Cwc8RckBupaDdw3Xx3rDzSRHwvO3uMpAYapLpIkVFRq0VmzQG80ZW9pTQ6UBTKx94H+C5jZ1dBudVUun0x6+sW+LZ8vcUY1dRqN62fPN7xSaZXEfgcgu1+pWb5lOYSwIDAQAB";
		String encryptString = null;
		if (encryptKey != null) {
			ImageBase64Util.setPublic_key( encryptKey );
			encryptString=ImageBase64Util.imageToBase64( encryptImageContent );
		} else {
			encryptString = "检查参数";
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(encryptString);

	}

	@RequestMapping(value = "optimizeEncryptImage")
	public void optimizeEncrypt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String encryptImageContent = request.getParameter("encryptImageContent");
		String encryptKey = request.getParameter("encryptkey");
		String encryptfileString = null;
		if (encryptKey != null) {
			ImageBase64Util.setPublic_key( encryptKey );
			encryptfileString=ImageBase64Util.imageToBase64New( encryptImageContent );
		} else {
			encryptfileString = "检查参数";
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(encryptfileString);

	}
}

package com.controller;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.RSAUtil;

@Controller
public class EncryptAndDencrypt extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileInputStream inputFile;

	@RequestMapping(value = "encrypt")
	public void encrypt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		RSAUtil rsa = new RSAUtil();
		String encryptContent = request.getParameter("encryptContent");
		String encryptKey = request.getParameter("encryptKey");
		// encryptContent = new String(encryptContent.getBytes("iso-8859-1"),
		// "UTF-8");
		String encryptString = null;
		if (encryptKey == null) {
			Properties prop = new Properties();
			inputFile = new FileInputStream(this.getClass().getClassLoader()
					.getResource("config.properties").getPath());
			prop.load(inputFile);

			encryptString = rsa.encrypt(encryptContent,
					prop.getProperty("encryptkey"));
		} else {
			encryptString = rsa.encrypt(encryptContent, encryptKey);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(encryptString);

	}

	@RequestMapping(value = "decrypt")
	public void dencrypt(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		RSAUtil rsa = new RSAUtil();
		String decryptContent = request.getParameter("decryptContent");
		String decryptKey = request.getParameter("decryptKey");
		// decryptContent = new String(decryptContent.getBytes("iso-8859-1"),
		// "UTF-8");

		String decryptString = null;
		if (decryptKey == null) {
			Properties prop = new Properties();
			inputFile = new FileInputStream(this.getClass().getClassLoader()
					.getResource("config.properties").getPath());
			prop.load(inputFile);

			decryptString = rsa.dencrypt(decryptContent,
					prop.getProperty("decryptkey"));
		} else {
			decryptString = rsa.dencrypt(decryptContent, decryptKey);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(decryptString);

	}

}

package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author sunjie
 *
 */
@SuppressWarnings("deprecation")
@Controller
public class RecordBankData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds;
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy" + "." + "MM"
			+ "." + "dd");
	private String fundType = "";
	private String bankName = "";
	private String channelCode = "";
	private String executor = "";
	private String testTarget = "";
	private String orderAmount = "";
	private String bankOrderNo = "";
	private String orgFundinOrderNo = "";
	private String evnType = "";
	private String recordDate = "";

	@RequestMapping(value = "recordBankData")
	public void record(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		fundType = request.getParameter("fundType");
		bankName = request.getParameter("bankName");
		channelCode = request.getParameter("channelCode");
		executor = request.getParameter("executor");
		if (!request.getParameter("testTarget").isEmpty()) {
			testTarget = request.getParameter("testTarget");
		} else {
			testTarget = "";
		}
		orderAmount = request.getParameter("orderAmount");
		bankOrderNo = request.getParameter("bankOrderNo");
		if (!request.getParameter("orgFundinOrderNo").isEmpty()) {
			orgFundinOrderNo = request.getParameter("orgFundinOrderNo");
		} else {
			orgFundinOrderNo = "";
		}
		evnType = request.getParameter("evnType");
		recordDate = fmt.format(new Date());
		
		try {
			returnRecordResult(request, response, record());
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(bankOrderNo + "保存失败!" + "<br/>" + e);
		}
		
//		try {
//			bdt.collectBankDataAndSendMail("24");
//		} catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (WriteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}

	private void returnRecordResult(HttpServletRequest request,
			HttpServletResponse response, int i) throws IOException {
		if (i != 0) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(bankOrderNo + "保存成功!");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(bankOrderNo + "保存失败!");
		}
	}

	private DataSource getDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/DevDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	private int record() {
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		String recordSql = "insert into BANK_DATA (RECORD_DATE, FUND_TYPE, BANK_NAME, CHANNEL_CODE, EXECUTOR, TEST_TARGET, ORDER_AMOUNT, BANK_ORDER_NO, ORG_FUNDIN_ORDER_NO, ENV_TYPE) values ('"
				+ recordDate
				+ "', '"
				+ fundType
				+ "', '"
				+ bankName
				+ "', '"
				+ channelCode
				+ "', '"
				+ executor
				+ "', '"
				+ testTarget
				+ "', '"
				+ orderAmount
				+ "', '"
				+ bankOrderNo
				+ "', '"
				+ orgFundinOrderNo + "', '" + evnType + "')";
		return sjt.update(recordSql);
	}

	public static void main(String[] args) {

	}
}

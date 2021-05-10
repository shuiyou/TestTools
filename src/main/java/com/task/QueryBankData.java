package com.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.DbEnviornment;

@Controller
public class QueryBankData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	@RequestMapping(value = "queryBankData")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String date = request.getParameter("date");
		String bankOrderNo = request.getParameter("bankOrderNo");
		String querySql = "";
		if (date != null) {
			querySql = "select * from BANK_DATA where RECORD_DATE = '" + date
					+ "'";
		}
		if (bankOrderNo != null) {
			querySql = "select * from BANK_DATA where BANK_ORDER_NO = '"
					+ bankOrderNo + "'";
		}
		response.setCharacterEncoding("UTF-8");
		out = response.getWriter();
		out.write("<table border=\"2\" class=\"table table-striped\">");
		out.write("<tr>");
		out.write("<td>记录日期</td> <td>支付方式</td> <td>银行名称</td> <td>渠道编码</td> <td>执行者</td> <td>测试目的</td> <td>订单金额</td> <td>银行订单号</td> <td>原入款订单号</td> <td>环境</td>");
		out.write("<tr>");

		DbEnviornment de = new DbEnviornment();
		de.Connect("10.65.193.12:1521", "whpay", "whqa", "whqa");
		try {
			ResultSet rs = DbEnviornment.statement.executeQuery(querySql);
			while (rs.next()) {
				out.write("<tr>");
				out.write("<td>" + rs.getString("RECORD_DATE") + "</td>");
				out.write("<td>" + rs.getString("FUND_TYPE") + "</td>");
				out.write("<td>" + rs.getString("BANK_NAME") + "</td>");
				out.write("<td>" + rs.getString("CHANNEL_CODE") + "</td>");
				out.write("<td>" + rs.getString("EXECUTOR") + "</td>");
				out.write("<td>" + rs.getString("TEST_TARGET") + "</td>");
				out.write("<td>" + rs.getString("ORDER_AMOUNT") + "</td>");
				out.write("<td>" + rs.getString("BANK_ORDER_NO") + "</td>");
				out.write("<td>" + rs.getString("ORG_FUNDIN_ORDER_NO")
						+ "</td>");
				out.write("<td>" + rs.getString("ENV_TYPE") + "</td>");
				out.write("</tr>");
			}
			rs.close();
			com.common.DbEnviornment.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write("</table>");
	}
}

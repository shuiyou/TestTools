package com.controller;

import java.io.IOException;
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
@Controller
@SuppressWarnings("deprecation")
public class QueryCmember extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds;

	@RequestMapping(value = "queryCmember")
	public void cleanCmember(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String partnerId = request.getParameter("partnerId");
		String mobile = request.getParameter("mobile");
		String env = request.getParameter("env");

		response.setCharacterEncoding("UTF-8");
		String cmemberId = query(partnerId, mobile, env);
		if (!cmemberId.equals("")) {
			response.getWriter().write(cmemberId);
		} else {
			response.getWriter().write("没有会员信息!");
		}
	}

	private DataSource getFuncCmemberDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/CmemberDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	private DataSource getTestCmemberDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/TestCmemberDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	private String query(String partnerId, String mobile, String env) {
		String cmemberId = "";
		String queryMemberIdSql = "select member_id from cmember.t_member_identity where partner_id = '"
				+ partnerId + "' and identity = '" + mobile + "'";
		SimpleJdbcTemplate sjt = null;
		if (env.equals("func")) {
			sjt = new SimpleJdbcTemplate(getFuncCmemberDataSource());
		}
		if (env.equals("test")) {
			sjt = new SimpleJdbcTemplate(getTestCmemberDataSource());
		}
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			cmemberId = (sjt.queryForList(queryMemberIdSql).get(0)
					.get("member_id")).toString();
		}
		System.out.println(cmemberId);
		return "" + cmemberId;
	}

	public static void main(String[] args) {
		QueryCmember test = new QueryCmember();
		test.query("200003670082", "13681935087", "func");
	}
}

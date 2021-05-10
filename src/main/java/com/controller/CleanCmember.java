package com.controller;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weihui.basis.inf.ucs.support.annotation.advice.EUCacheBuilder;
import com.weihui.ma.service.facade.IMemcachedFacade;

/**
 * 
 * @author sunjie
 *
 */
@Controller
@SuppressWarnings("deprecation")
public class CleanCmember extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds;

	@RequestMapping(value = "cleanCmember")
	public void cleanCmember(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String cmemberId = request.getParameter("cmemberId");
		String env = request.getParameter("env");
		String memberId = getMemberId(cmemberId, env);

		returnCleanResult(request, response, clean(cmemberId, memberId, env),
				refreshMember(env), refreshCmember(env));

	}

	private void returnCleanResult(HttpServletRequest request,
			HttpServletResponse response, int i, String refreshMemberResult,
			String refreshCmemberResult) throws IOException {
		if (i != 0 && refreshMemberResult.equals("OK")
				&& refreshCmemberResult.equals("OK")) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("清除数据成功, 缓存刷新成功!");
		} else if (i != 0 && !refreshMemberResult.equals("OK")
				|| !refreshCmemberResult.equals("OK")) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("清除数据成功, 缓存刷新失败!");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("没有数据被清除!");
		}
	}

	private DataSource getFuncMemberDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/MemberDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	private DataSource getTestMemberDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/TestMemberDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
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

	private String getMemberId(String cmemberId, String env) {
		String memberId = "";
		String queryMemberIdSql = "select member_id from member.tm_member_identity t where t.identity = '"
				+ cmemberId + "'";
		SimpleJdbcTemplate sjt = null;
		if (env.equals("func")) {
			sjt = new SimpleJdbcTemplate(getFuncMemberDataSource());
		}
		if (env.equals("test")) {
			sjt = new SimpleJdbcTemplate(getTestMemberDataSource());
		}
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			memberId = (sjt.queryForList(queryMemberIdSql).get(0)
					.get("member_id")).toString();
		}
		return "" + memberId;
	}

	private int clean(String cmemberId, String memberId, String env) {
		int result = 0;
		if (env.equals("func")) {
			SimpleJdbcTemplate sjtFuncCmember = new SimpleJdbcTemplate(
					getFuncCmemberDataSource());
			SimpleJdbcTemplate sjtFuncMember = new SimpleJdbcTemplate(
					getFuncMemberDataSource());
			String deleteT_member = "delete from cmember.t_member where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_identity = "delete from cmember.t_member_identity where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_info = "delete from cmember.t_member_info where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_set = "delete from cmember.t_member_set where member_id = '"
					+ cmemberId + "'";

			String deleteEntitySql = "delete from member.tr_verify_ref where member_id = '"
					+ memberId + "'";
			String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id = '"
					+ memberId + "'";
			String deleteIdentitySql = "delete from member.tm_member_identity where member_id = '"
					+ memberId + "'";
			String deleteTmMemberSql = "delete from member.tm_member where member_id = '"
					+ memberId + "'";

			result = sjtFuncCmember.update(deleteT_member)
					+ sjtFuncCmember.update(deleteT_member_identity)
					+ sjtFuncCmember.update(deleteT_member_info)
					+ sjtFuncCmember.update(deleteT_member_set)
					+ sjtFuncMember.update(deleteEntitySql)
					+ sjtFuncMember.update(deletePersonalMemberSql)
					+ sjtFuncMember.update(deleteIdentitySql)
					+ sjtFuncMember.update(deleteTmMemberSql);
		}
		if (env.equals("test")) {
			SimpleJdbcTemplate sjtTestCmember = new SimpleJdbcTemplate(
					getTestCmemberDataSource());
			SimpleJdbcTemplate sjtTestMember = new SimpleJdbcTemplate(
					getTestMemberDataSource());
			String deleteT_member = "delete from cmember.t_member where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_identity = "delete from cmember.t_member_identity where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_info = "delete from cmember.t_member_info where member_id = '"
					+ cmemberId + "'";
			String deleteT_member_set = "delete from cmember.t_member_set where member_id = '"
					+ cmemberId + "'";

			String deleteEntitySql = "delete from member.tr_verify_entity where verify_entity_id in (select verify_entity_id from member.tr_verify_ref where member_id = '"
					+ memberId + "'";
			String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id = '"
					+ memberId + "'";
			String deleteIdentitySql = "delete from member.tm_member_identity where member_id = '"
					+ memberId + "'";
			String deleteTmMemberSql = "delete from member.tm_member where member_id = '"
					+ memberId + "'";
			result = sjtTestCmember.update(deleteT_member)
					+ sjtTestCmember.update(deleteT_member_identity)
					+ sjtTestCmember.update(deleteT_member_info)
					+ sjtTestCmember.update(deleteT_member_set)
					+ sjtTestMember.update(deleteEntitySql)
					+ sjtTestMember.update(deletePersonalMemberSql)
					+ sjtTestMember.update(deleteIdentitySql)
					+ sjtTestMember.update(deleteTmMemberSql);
		}
		return result;
	}

	private String refreshMember(String env) {
		String response = "";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IMemcachedFacade.class);
		if (env.equals("func")) {
			factory.setAddress("http://10.65.209.11:8081/ma-web/MemcachedFacade");
		}
		if (env.equals("test")) {
			factory.setAddress("http://10.65.211.14:8095/ma-web/MemcachedFacade");
		}
		IMemcachedFacade service = (IMemcachedFacade) factory.create();
		try {
			response = service.refresh(null);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		System.out.println("Refresh Member " + response + "!");
		return response;
	}

	@SuppressWarnings("rawtypes")
	private String refreshCmember(String env) {
		ApplicationContext ap = new ClassPathXmlApplicationContext(
				"classpath:/applicationContextUCS.xml");
		EUCacheBuilder cb = new EUCacheBuilder();
		cb.setName("index.com.weihui.cache.finance.public.cmember");
		if (env.equals("func")) {
			cb.setNameServerAddress("tcp://10.65.209.14:1978");
		}
		if (env.equals("test")) {
			cb.setNameServerAddress("tcp://10.65.211.11:1978");
		}
		cb.setLocalCacheMaxSize(1000);
		cb.setListeners("ucs.support.annotation.commonListener");

		cb.setApplicationContext(ap);
		try {
			cb.build().flush();
		} catch (Exception e) {
			System.out.println("Refresh Cmember FAILD!");
			return "FAILD";
		}
		System.out.println("Refresh Cmember OK!");
		return "OK";

	}

	public static void main(String[] args) {
		CleanCmember test = new CleanCmember();
		// test.clean("10000000486680", "100005484246", "func");
		// System.out.println(test.refreshCmember("func"));
		test.refreshCmember("test");
	}
}

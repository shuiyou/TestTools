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
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.weihui.ma.service.facade.IMemcachedFacade;

/**
 * 
 * @author sunjie
 *
 */
@SuppressWarnings("deprecation")
@Controller
public class CleanMemberTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds;

	@RequestMapping(value = "cleanMemberTest")
	public void clean(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);

		returnCleanResult(request, response, clean(memberId), refresh());

		refresh();

	}

	private void returnCleanResult(HttpServletRequest request,
			HttpServletResponse response, int i, String refreshResult)
			throws IOException {
		if (i != 0 && refreshResult.equals("OK")) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("清除成功! MA缓存刷新成功!");
		} else if (i != 0 && !refreshResult.equals("OK")) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("清除成功! MA缓存刷新失败,请手动刷新!");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("没有数据被清除, 请检查输入的member_id是否正确!");
		}
	}

	private DataSource getDataSource() {
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

	private int clean(String memberId) {
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		String deleteEntitySql = "delete from member.tr_verify_entity where verify_entity_id in (select verify_entity_id from member.tr_verify_ref where member_id = '"
				+ memberId + "')";
		String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id = '"
				+ memberId + "'";
		String deleteIdentitySql = "delete from member.tm_member_identity where member_id = '"
				+ memberId + "'";
		String deleteTmMemberSql = "delete from member.tm_member where member_id = '"
				+ memberId + "'";
		return sjt.update(deleteEntitySql)
				+ sjt.update(deletePersonalMemberSql)
				+ sjt.update(deleteIdentitySql) + sjt.update(deleteTmMemberSql);
	}

	private String refresh() {
		String response = "";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IMemcachedFacade.class);
		factory.setAddress("http://testbasis.whintra.com/ma-web/MemcachedFacade");
		IMemcachedFacade service = (IMemcachedFacade) factory.create();
		try {
			response = service.refresh(null);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		return response;
	}
	
	public static void main (String[] args) {
		CleanMemberTest test = new CleanMemberTest();
		test.clean("100005335331");
	}
}

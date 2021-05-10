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
public class CleanMemberInfo extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds;

	@RequestMapping(value = "clean")
	public void clean(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String entity = request.getParameter("entity");
		String merchantId = request.getParameter("merchantId");
		String cleanType = request.getParameter("cleanType");
		String pid = getPlatformType(merchantId);
		System.out.println("merchantId=" + merchantId);
		System.out.println("pid=" + pid);
		System.out.println(entity);
		System.out.println(cleanType);

		if (cleanType.equals("MOBILE")) {
			returnCleanResult(request, response, cleanMobile(entity, pid), refresh());
		}

		if (cleanType.equals("EMAIL")) {
			returnCleanResult(request, response, cleanEmail(entity, pid), refresh());
		}

		if (cleanType.equals("REALNAME")) {
			returnCleanResult(request, response, cleanRealName(entity, pid), refresh());
		}

		if (cleanType.equals("ALL")) {
			returnCleanResult(request, response, cleanAll(entity, pid), refresh());
		}

		refresh();

		// String id = request.getParameter("id");
		// String sql = "select * from wops.t_transaction_order where id = '" +
		// id
		// + "'";
		// System.out.println(sql);
		// try {
		// Context ctx = new InitialContext();
		//
		// // 此处要添加的是查找数据源的名字 但是要加前缀java:comp/env
		// DataSource ds =
		// (DataSource)ctx.lookup("java:comp/env/MemberDataSource");
		// Connection conn = ds.getConnection(); // 通过数据源获得数据源中的连接
		// PreparedStatement pstmt = conn.prepareStatement(sql);
		// ResultSet rs = pstmt.executeQuery();
		// while (rs.next()) {
		// System.out.println(rs.getString(2));
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

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
			response.getWriter().write(
					"没有数据被清除,请检查输入条件是否正确!(未绑定手机或邮箱的情况下,使用手机或邮箱作为条件进行清除会失败.)");
		}
	}

	private DataSource getReaderSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/ReaderSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	private DataSource getDataSource() {
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

	private String getPlatformType (String merchantId) {
		String pid = "";
		String queryPidSql = "select platform_type from acs.t_merchant_key where MERCHANT_ID = '"+ merchantId + "'";
		System.out.println(queryPidSql);
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getReaderSource());
		if (sjt.queryForList(queryPidSql).size() > 0) {
			pid = (sjt.queryForList(queryPidSql).get(0).get("platform_type")).toString();
		}
		return "" + pid;
	}

	private int cleanMobile(String entity, String pid) {
		String queryMemberIdSql = "select member_id from member.tm_member_identity where identity = '"
				+ entity + "' and pid = '" + pid + "'";
		StringBuilder memberId = new StringBuilder("(");
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			int k = sjt.queryForList(queryMemberIdSql).size();
			for (int i = 0; i < k; i++) {
				memberId.append("'");
				memberId.append((String) sjt.queryForList(queryMemberIdSql)
						.get(i).get("member_id"));
				memberId.append("', ");
			}
		} else {
			memberId.append("'', ");
		}
		memberId.replace(memberId.length() - 2, memberId.length(), ")");

//		String deleteMobileSql = "delete from member.tr_verify_entity where verify_type = '11' and verify_entity_id in (select verify_entity_id from member.tr_verify_ref  where member_id in "
//				+ memberId + " and pid = '" + pid + "')";

		String deleteMobileSql = "delete from member.tr_verify_ref WHERE verify_id IN (" +
				"  SELECT VERIFY_ID" +
				"  FROM member.tr_verify_ref a, member.tr_verify_entity b" +
				"  WHERE a.member_id IN "+memberId+" AND b.verify_type = 11 AND a.VERIFY_ENTITY_ID = b.VERIFY_ENTITY_ID" + ")";





        String deleteEntitySql = "delete from member.tm_member_identity where member_id in "
				+ memberId + " and identity_type = '2' and pid = '" + pid + "'";
		System.out.println(deleteMobileSql);
		System.out.println(deleteEntitySql);
		return sjt.update(deleteMobileSql) + sjt.update(deleteEntitySql);
	}

	private int cleanEmail(String entity, String pid) {
		String queryMemberIdSql = "select member_id from member.tm_member_identity where identity = '"
				+ entity + "' and pid = '" + pid + "'";
		StringBuilder memberId = new StringBuilder("(");
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			int k = sjt.queryForList(queryMemberIdSql).size();
			for (int i = 0; i < k; i++) {
				memberId.append("'");
				memberId.append((String) sjt.queryForList(queryMemberIdSql)
						.get(i).get("member_id"));
				memberId.append("', ");
			}
		} else {
			memberId.append("'', ");
		}
		memberId.replace(memberId.length() - 2, memberId.length(), ")");

//		String deleteEmailSql = "delete from member.tr_verify_entity where verify_type = '12' and verify_entity_id in (select verify_entity_id from member.tr_verify_ref  where member_id in "
//				+ memberId + " and pid = '" + pid + "')";


		String deleteEmailSql = "delete from member.tr_verify_ref WHERE verify_id IN (" +
				"  SELECT VERIFY_ID" +
				"  FROM member.tr_verify_ref a, member.tr_verify_entity b" +
				"  WHERE a.member_id IN "+memberId+" AND b.verify_type = 12 AND a.VERIFY_ENTITY_ID = b.VERIFY_ENTITY_ID" + ")";




		String deleteEntitySql = "delete from member.tm_member_identity where member_id in "
				+ memberId + " and identity_type = '1' and pid = '" + pid + "'";
		System.out.println(deleteEmailSql);
		System.out.println(deleteEntitySql);
		return sjt.update(deleteEmailSql) + sjt.update(deleteEntitySql);
	}

	private int cleanRealName(String entity, String pid) {
		String queryMemberIdSql = "select member_id from member.tm_member_identity where identity = '"
				+ entity + "' and pid = '" + pid + "'";
		StringBuilder memberId = new StringBuilder("(");
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			int k = sjt.queryForList(queryMemberIdSql).size();
			for (int i = 0; i < k; i++) {
				memberId.append("'");
				memberId.append((String) sjt.queryForList(queryMemberIdSql)
						.get(i).get("member_id"));
				memberId.append("', ");
			}
		} else {
			memberId.append("'', ");
		}
		memberId.replace(memberId.length() - 2, memberId.length(), ")");

		String deleteRealSql = "delete from member.tr_verify_ref WHERE verify_id IN (" +
				"  SELECT VERIFY_ID" +
				"  FROM member.tr_verify_ref a, member.tr_verify_entity b" +
				"  WHERE a.member_id IN "+memberId+" AND b.verify_type = 1 AND a.VERIFY_ENTITY_ID = b.VERIFY_ENTITY_ID" + ")";



		String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id in "
				+ memberId;
		return sjt.update(deleteRealSql)
				+ sjt.update(deletePersonalMemberSql);
	}

	private int cleanAll(String entity, String pid) {
		String queryMemberIdSql = "select member_id from member.tm_member_identity where identity = '"
				+ entity + "' and pid = '" + pid + "'";
		StringBuilder memberId = new StringBuilder("(");
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		if (sjt.queryForList(queryMemberIdSql).size() > 0) {
			int k = sjt.queryForList(queryMemberIdSql).size();
			for (int i = 0; i < k; i++) {
				memberId.append("'");
				memberId.append((String) sjt.queryForList(queryMemberIdSql)
						.get(i).get("member_id"));
				memberId.append("', ");
			}
		} else {
			memberId.append("'', ");
		}
		memberId.replace(memberId.length() - 2, memberId.length(), ")");

		String deleteEntityInfoSql = "delete from member.tr_verify_ref  where member_id in "
				+ memberId + " and pid = '" + pid + "'";
		String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id in "
				+ memberId;
		String deleteEntitySql = "delete from member.tm_member_identity where member_id in "
				+ memberId + " and identity_type in ('1', '2') and pid = '" + pid + "'";
		System.out.println(deleteEntitySql);
		return sjt.update(deleteEntityInfoSql)
				+ sjt.update(deletePersonalMemberSql)
				+ sjt.update(deleteEntitySql);
	}

	private String refresh() {
		String response = "";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(IMemcachedFacade.class);
		factory.setAddress("http://10.65.209.11:8081/ma-web/MemcachedFacade");
		IMemcachedFacade service = (IMemcachedFacade) factory.create();
		try {
			response = service.refresh(null);
		} catch (Exception e) {
			System.out.println("GET RESPONSE ERROR: " + e.getMessage());
		}
		return response;
	}


}

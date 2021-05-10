package com.controller;

import com.common.DbEnviornment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanxiaodi on 18/11/6.
 */
@Controller
public class CleanErmInfo extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	DataSource ds;
	DataSource member_ds;
	DataSource flow_ds;
	int result;
	boolean ret;



	@RequestMapping(value = "cleanErm")
	public void cleanErminfo(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		String memberId = request.getParameter( "memberId" );
		String deleteType = request.getParameter( "selectCleanType" );
		returnCleanRrmResult(request,response,cleanAll( memberId,deleteType ),memberId,deleteType);



	}

	private DataSource getErmDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/ErmDataSource");
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
			flow_ds = (DataSource) ctx.lookup("java:comp/env/MemberDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flow_ds;
	}

	private DataSource getFlowDataSource() {
		Context ctx;
		try {
			ctx = new InitialContext();
			member_ds = (DataSource) ctx.lookup("java:comp/env/FlowDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member_ds;
	}


	private boolean assertMemberDate(String memberId){
		try
		{
			DbEnviornment de = new DbEnviornment();
			de.Connect("10.65.193.11:1521", "whpay", "READER", "tF1P7IC7oKa6ua");
			String querySql ="select memberId from member.tm_member_identity where member_id = '"
					+ memberId + "'";
			ResultSet rs = de.statement.executeQuery(querySql);
			if(rs!=null){
				ret = true;
			}else {
				ret = false;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	private  void cleanFlowData(String memberId){
		SimpleJdbcTemplate flow_sjt = new SimpleJdbcTemplate(getFlowDataSource());
		String delete_flowRuIdLink_sql = "delete from orderflow.act_ru_identitylink where user_id_='"+memberId +"'";

		String delete_flowTask_sql ="delete from orderflow.act_ru_task where proc_inst_id_ not in (select proc_inst_id_ from orderflow.act_ru_identitylink where proc_inst_id_ is not null) and proc_inst_id_ is not null";

		String delete_flowVar_sql="delete from orderflow.act_ru_variable where proc_inst_id_ not in (select proc_inst_id_ from orderflow.act_ru_identitylink where proc_inst_id_ is not null) and proc_inst_id_ is not null";

		String delete_flowExecution="delete from orderflow.act_ru_execution where proc_inst_id_ not in (select proc_inst_id_ from orderflow.act_ru_identitylink where proc_inst_id_ is not null) and proc_inst_id_ is not null";



		flow_sjt.update( delete_flowRuIdLink_sql );

		flow_sjt.update( delete_flowVar_sql );

				try
				{
					flow_sjt.update( delete_flowTask_sql );
					flow_sjt.update( delete_flowExecution );
				}catch (Exception e){
					e.printStackTrace();
					Map member = new HashMap<String,String>();
					List<Map<String, Object>> a =flow_sjt.queryForList( "select id_ from orderflow.act_ru_task where proc_inst_id_ not in (select proc_inst_id_ from orderflow.act_ru_identitylink where proc_inst_id_ is not null) and proc_inst_id_ is not null",
							member );
					int length = a.size();
					for(int i = 0;i<length;i++){
						Map id = a.get( i );
						String id_ = (String)id.get( "id_" );
						String identitylink_sql ="delete from orderflow.act_ru_identitylink where task_id_='"+id_+"'";
						//String task_sql ="delete from orderflow.act_ru_task where task_id_='"+id_+"'";
						flow_sjt.update( identitylink_sql );

					}
					flow_sjt.update( delete_flowTask_sql );
					flow_sjt.update( delete_flowExecution );
				}


	}

	private int cleanAll(String memberId,String deleteType) {
		//获取erm和member库
		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getErmDataSource());
		SimpleJdbcTemplate member_sjt = new SimpleJdbcTemplate(getDataSource());

		//所有需要执行的删除sql
		String delete_ErmEnterpise_InfoSql = "delete from erm.t_enterprise where member_id='"+memberId+"'";

		String delete_EnterpriseDetail_Sql = "delete from erm.t_enterprise_detail_info where member_id='"
				+ memberId+"'";

		String delete_ErmEnterpriseLicense_Sql = "delete from erm.t_enterprise_license where member_id='"
				+ memberId +"'";

		String delete_EnterprisePartner_Sql = "delete from erm.t_enterprise_partner where member_id='"
				+ memberId+"'" ;

		String delete_Management_InfoSql="delete from erm.t_management_info where member_id='"
				+ memberId+"'" ;

		String delete_ProductApply_Sql="delete from erm.t_product_apply where member_id='"
				+ memberId+"'" ;

		String delete_UploadData_sql = "delete from erm.t_upload_data where member_id='"
				+ memberId+"'" ;

		String delete_ActualControlle_sql= "delete from erm.t_actual_controller where member_id='"
				+ memberId +"'";

		String delete_application_InfoSql = "delete from erm.t_application_info where member_id='"
				+ memberId +"'";

		String delete_AuditTask_Sql = "delete from erm.t_audit_task where member_id='"
				+ memberId +"'" ;

		String delete_erm_corporationInfo_Sql = "delete from erm.t_corporation_info where member_id='"
				+ memberId +"'" ;

		String delete_ContactInfo_Sql = "delete from erm.audit_content where member_id='"
				+ memberId +"'";

		/**
		 * member表清除sql
		 * */
		String deleteEntitySql = "delete from member.tr_verify_ref where member_id = '"
				+ memberId + "'";
		String deletePersonalMemberSql = "delete from member.tr_personal_member where member_id = '"
				+ memberId + "'";
		String deleteIdentitySql = "delete from member.tm_member_identity where member_id = '"
				+ memberId + "'";
		String deleteTmMemberSql = "delete from member.tm_member where member_id = '"
				+ memberId + "'";


		if(deleteType.equals("OnlyRegister")){
			result = sjt.update(delete_ErmEnterpise_InfoSql)
					+ sjt.update(delete_EnterpriseDetail_Sql)
					+ sjt.update(delete_ErmEnterpriseLicense_Sql)
					+ sjt.update( delete_EnterprisePartner_Sql )
					+ sjt.update( delete_Management_InfoSql )
					+ sjt.update( delete_ProductApply_Sql )
					+ sjt.update( delete_UploadData_sql )
					+ sjt.update( delete_ActualControlle_sql )
					+ sjt.update( delete_application_InfoSql )
					+ sjt.update( delete_erm_corporationInfo_Sql )
					+ sjt.update( delete_AuditTask_Sql )
					;
			cleanFlowData( memberId );
			return result;
		}else {
			cleanFlowData( memberId );
			return sjt.update(delete_ErmEnterpise_InfoSql)
					+ sjt.update(delete_EnterpriseDetail_Sql)
					+ sjt.update(delete_ErmEnterpriseLicense_Sql)
					+ sjt.update( delete_EnterprisePartner_Sql )
					+ sjt.update( delete_Management_InfoSql )
					+ sjt.update( delete_ProductApply_Sql )
					+ sjt.update( delete_UploadData_sql )
					+ sjt.update( delete_ActualControlle_sql )
					+ sjt.update( delete_application_InfoSql )
					+ sjt.update( delete_erm_corporationInfo_Sql )
					+ sjt.update( delete_AuditTask_Sql )
					+ sjt.update( delete_ContactInfo_Sql
					+member_sjt.update(deleteEntitySql)
					+ member_sjt.update(deletePersonalMemberSql)
					+ member_sjt.update(deleteIdentitySql) + member_sjt.update(deleteTmMemberSql) );
		}

	}

	private void returnCleanRrmResult(HttpServletRequest request,
			HttpServletResponse response, int i,String memberId,String deleteType)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		if (i != 0) {
			if(deleteType.equals( "OnlyRegister" )){
				response.getWriter().write("恢复到注册状态!");
			}else {
				response.getWriter().write("清除成功!");
			}

		}else {
			if(assertMemberDate( memberId )){
				response.getWriter().write(
						"会员"+memberId+"不在ERM库");
			}else {
				response.getWriter().write(
						"会员"+memberId+"不在在ERM库和member库");
			}

		}
	}
}
